//package example.com.codechallenge;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
//import example.com.codechallenge.dto.SnippetRequest;
//import example.com.codechallenge.dto.SnippetResponse;
//import example.com.codechallenge.service.SnippetService;
//import example.com.codechallenge.service.rediscache.CacheService;
//import example.com.codechallenge.utils.JsonConverter;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.time.LocalDateTime;
//
//@RunWith(SpringRunner.class)
//
//@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ CodeChallengeApplication.class })
//public class AccountControllerTest{
//
//	private MockMvc mockMvc;
//
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//	@MockBean
//	protected SnippetService snippetService;
//
//	@MockBean
//	protected CacheService cacheService;
//
//	@Before
//	public void setUp() {
//		 this.mockMvc = webAppContextSetup(webApplicationContext).build();
//	}
//
////	@Test
////	public void should_CreateAccount_When_ValidRequest() throws Exception {
////
////		when(accountServiceMock.createAccount(any(Account.class))).thenReturn(12345L);
////
////		mockMvc.perform(post("/api/account")
////			   .contentType(MediaType.APPLICATION_JSON)
////			   .content("{ \"accountType\": \"SAVINGS\", \"balance\": 5000.0 }")
////			   .accept(MediaType.APPLICATION_JSON))
////			   .andExpect(status().isCreated())
////			   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
////			   .andExpect(header().string("Location", "/api/account/12345"))
////			   .andExpect(jsonPath("$.accountId").value("12345"))
////			   .andExpect(jsonPath("$.accountType").value("SAVINGS"))
////			   .andExpect(jsonPath("$.balance").value(5000));
////	}
////
////	@Test
////	public void should_GetAccount_When_ValidRequest() throws Exception {
////
////		/* setup mock */
////		Account account = new Account(12345L, EnumAccountType.SAVINGS, 5000.0);
////		when(accountServiceMock.loadAccount(12345L)).thenReturn(account);
////
////		mockMvc.perform(get("/api/account/12345")
////			   .accept(MediaType.APPLICATION_JSON))
////			   .andExpect(status().isOk())
////			   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
////			   .andExpect(jsonPath("$.accountId").value(12345))
////			   .andExpect(jsonPath("$.accountType").value("SAVINGS"))
////			   .andExpect(jsonPath("$.balance").value(5000.0));
////	}
////
////	@Test
////	public void should_Return404_When_AccountNotFound() throws Exception {
////
////		/* setup mock */
////		when(accountServiceMock.loadAccount(12345L)).thenReturn(null);
////
////		mockMvc.perform(get("/api/account/12345")
////			   .accept(MediaType.APPLICATION_JSON))
////			   .andExpect(status().isNotFound());
////	}
//
//	@org.junit.jupiter.api.Test
//	public void shouldSaveSnippet() throws Exception {
//		// given
//		LocalDateTime creationDate = LocalDateTime.of(2018, 5, 20, 20, 51, 16);
//		SnippetRequest snippetRequest = new SnippetRequest("recipe",30,"1 apple");
//		SnippetResponse response = new SnippetResponse(snippetRequest,"http://localhost:8080/snippets/recipe",creationDate.toString());
////		PostDto post = new PostDto("Title", "content", creationDate);
//
//		// when
//		when(snippetService.saveSnippet(snippetRequest)).thenReturn(response);
//
//		// then
//		String json = JsonConverter.getJson(snippetRequest);
//
//		mockMvc.perform(post("/snippets")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(json)
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isCreated())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(jsonPath("$.url").value("http://localhost:8080/snippets/recipe"))
//				.andExpect(jsonPath("$.name").value("recipe"))
//				.andExpect(jsonPath("$.expires_at").value(creationDate.toString()))
//				.andExpect(jsonPath("$.snippet").value("1 apple"));
//
////		mockMvc.perform(post("/snippets")
////				.accept(MediaType.APPLICATION_JSON)).content(json)
////				.andExpect(status().isCreated())
////				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
////				.andExpect((ResultMatcher) jsonPath("$.url", is("http://localhost:8080/snippets/recipe")))
////				.andExpect((ResultMatcher) jsonPath("$.name", is("recipe")))
////				.andExpect((ResultMatcher) jsonPath("$.expires_at", is(creationDate.toString())))
////				.andExpect((ResultMatcher) jsonPath("$.snippet", is("1 apple")));
//
//	}
//}