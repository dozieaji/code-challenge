package example.com.codechallenge.rest;

import example.com.codechallenge.dto.SnippetRequest;
import example.com.codechallenge.dto.SnippetResponse;
import example.com.codechallenge.utils.JsonConverter;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
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
}
