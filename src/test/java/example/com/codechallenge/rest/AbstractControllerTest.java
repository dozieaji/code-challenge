package example.com.codechallenge.rest;

import example.com.codechallenge.service.SnippetService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebMvcTest
public abstract class AbstractControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;



	@MockBean
	protected SnippetService snippetService;


//	@Before
//	public void setUp() {
//		Mockito.reset(snippetService, cacheService);
//	}
	@Before
	public void setUp() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}


}
