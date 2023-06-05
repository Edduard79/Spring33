package com.example.Redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.Redis.Redis;


@Repository
public interface RedisRepo extends CrudRepository<Redis, Long> {
}
