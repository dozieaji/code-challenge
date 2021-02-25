package example.com.codechallenge.service.rediscache;

public interface RedisRepository<T> {

    /**
     * Add key-value pair to Redis.
     */
    void add(T obj, Object otherKey, long sessionTimeOut);
    /**
     * Add key-value pair to Redis.
     */
    void update(Object otherKey, long sessionTimeOut);
    /**
     * find an object
     */
    T findObj(Object otherKey);

}
