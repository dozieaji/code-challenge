package example.com.codechallenge.rest;

import example.com.codechallenge.dto.SnippetRequest;
import example.com.codechallenge.dto.SnippetResponse;
import example.com.codechallenge.utils.JsonConverter;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SnippetControllerTest extends AbstractControllerTest {

    @Test
    public void shouldSaveSnippet() throws Exception {
        // given
        LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
        SnippetRequest snippetRequest = new SnippetRequest("recipe", 30, "1 apple");
        SnippetResponse response = new SnippetResponse(snippetRequest, "http://localhost:8080/snippets/recipe", creationDate.toString());
        String json = JsonConverter.getJson(snippetRequest);

        // when
        when(snippetService.saveSnippet(snippetRequest)).thenReturn(response);
        mockMvc.perform(post("/snippets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.url").value("http://localhost:8080/snippets/recipe"))
                .andExpect(jsonPath("$.name").value("recipe"))
                .andExpect(jsonPath("$.expires_at").value(creationDate.toString()))
                .andExpect(jsonPath("$.snippet").value("1 apple"));

    }

    @Test
    public void should_increase_like_and_extend_time() throws Exception {
        // given
        LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
        SnippetRequest snippetRequest = new SnippetRequest("recipe", 30, "1 apple");
        SnippetResponse response = new SnippetResponse(snippetRequest, "http://localhost:8080/snippets/recipe", creationDate.toString());
        response.setLikes(1);

        // when
        when(snippetService.likeSnippet(snippetRequest.getName())).thenReturn(response);
        mockMvc.perform(post("/snippets/recipe/like")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.url").value("http://localhost:8080/snippets/recipe"))
                .andExpect(jsonPath("$.name").value("recipe"))
                .andExpect(jsonPath("$.expires_at").value(creationDate.toString()))
                .andExpect(jsonPath("$.snippet").value("1 apple"))
                .andExpect(jsonPath("$.likes").value("1"));

    }

    @Test
    public void should_get_snippet_When_valid_url() throws Exception {
        LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
        SnippetRequest snippetRequest = new SnippetRequest("recipe", 30, "1 apple");
        SnippetResponse response = new SnippetResponse(snippetRequest, "http://localhost:8080/snippets/recipe", creationDate.toString());

        when(snippetService.getSnippet("recipe")).thenReturn(response);

        mockMvc.perform(get("/snippets/recipe")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.url").value("http://localhost:8080/snippets/recipe"))
                .andExpect(jsonPath("$.name").value("recipe"))
                .andExpect(jsonPath("$.expires_at").value(creationDate.toString()))
                .andExpect(jsonPath("$.snippet").value("1 apple"));
    }
}
