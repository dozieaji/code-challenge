package example.com.codechallenge.service.rediscache;

import example.com.codechallenge.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheService<T> {
    @Autowired
    private RedisRepositoryImpl redisRepository;

    public <T> boolean saveRecordToRedis(T obj, String key, long sessionTimeOut){
        if (key == null) {
            log.error("Form number is NULL");
            return false;
        }
        String json = new JsonConverter().getJsonRecursive(obj);
        System.out.println(" Object to save redis "+json);
        redisRepository.add(json, key, sessionTimeOut);
        return true;
    }

    public <T> boolean updateRecordOnRedis(String key, long sessionTimeOut){
        if (key == null) {
            log.error("Form number is NULL");
            return false;
        }
        redisRepository.update(key, sessionTimeOut);
        return true;
    }

    public <T> T getRecordFromRedis(String key, Class<T> clazz) {
        Object obj = redisRepository.findObj(key);
        if (obj != null) {
            T fromJson = new JsonConverter().getElement(obj.toString(), clazz);
            return fromJson;
        }
        return null;
    }

}
