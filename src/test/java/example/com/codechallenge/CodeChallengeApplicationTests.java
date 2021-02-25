package example.com.codechallenge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ CodeChallengeApplication.class })
class CodeChallengeApplicationTests {

    @Test
    void contextLoads() {
    }

}
