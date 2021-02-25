package example.com.codechallenge;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class CodeChallengeApplication {


    @Value("${spring.redis.port}") private int redisPort;
    @Value("${spring.redis.host}") private String redisHost;

    public static void main(String[] args) {
        SpringApplication.run(CodeChallengeApplication.class, args);
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

        String host = redisHost;
        int port = redisPort;

        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.getPoolConfig().setMaxIdle(30);
        jedisConnectionFactory.getPoolConfig().setMinIdle(10);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> initRedis() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
