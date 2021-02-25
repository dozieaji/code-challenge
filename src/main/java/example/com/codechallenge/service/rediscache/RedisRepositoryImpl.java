package example.com.codechallenge.service.rediscache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository
@Slf4j
public class RedisRepositoryImpl implements RedisRepository<String> {
    private static final String store = "snippet";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;


    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    public void add(final String value, Object key, long sessionTimeOut) {
        hashOperations.put(store+key,key, value);
        if (sessionTimeOut > 0)
            redisTemplate.expire(store+key, sessionTimeOut, TimeUnit.SECONDS);
    }

    public void update(Object key, long sessionTimeOut) {
        boolean aBoolean = hashOperations.hasKey(store + key, key);

        if (aBoolean && sessionTimeOut > 0)
            redisTemplate.expire(store+key, sessionTimeOut, TimeUnit.SECONDS);
    }

    public String findObj(Object key){
        return (String) hashOperations.get(store+key,key);
    }

}
