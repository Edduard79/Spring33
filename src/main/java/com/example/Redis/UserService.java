package com.example.Redis;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Redis.UserJPA;
import com.example.Redis.UserRepoJPA;
import com.example.Redis.Redis;
import com.example.Redis.RedisRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepoJPA userRepoJPA;
    @Autowired
    private RedisRepo redisRepo;

    public Redis dataConverter(UserJPA user) {
        Redis redis = new Redis();
        BeanUtils.copyProperties(user, redis);
        return redis;
    }

    public UserJPA dataConverter(Redis user) {
        UserJPA redis = new UserJPA();
        BeanUtils.copyProperties(user, redis);
        return redis;
    }

    public UserJPA create(UserJPA userJPA) {
        if (userJPA == null) return null;
        userJPA.setId(null);
        return userRepoJPA.save(userJPA);
    }

    public UserJPA getOne(Long id) {
        Optional<Redis> redis = redisRepo.findById(id);
        if (redis.isPresent()) {
            return dataConverter(redis.get());
        } else {
            UserJPA userJPADB = userRepoJPA.getReferenceById(id);
            redisRepo.save(dataConverter(userJPADB));
            return userJPADB;
        }
    }

    public List<UserJPA> getAll() {
        return userRepoJPA.findAll();
    }

    public UserJPA update(Long id, UserJPA userJPA) {
        if (userJPA == null) return null;
        userJPA.setId(id);
        userRepoJPA.save(userJPA);

        Optional<Redis> userR = redisRepo.findById(id);
        if (userR.isPresent()) {
            redisRepo.deleteById(id);
        }
        return userJPA;
    }

    public void delete(Long id) {
        userRepoJPA.deleteById(id);
        redisRepo.deleteById(id);
    }

}
