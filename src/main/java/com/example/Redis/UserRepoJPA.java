package com.example.Redis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Redis.UserJPA;

@Repository
public interface UserRepoJPA extends JpaRepository<UserJPA, Long> {
}
