package com.cloud.user.repository;

import com.cloud.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByUserLine1AndUserLine2(String userLine1, String userLine2);
    List<User> findByUserLine1(String userLine1);
    List<User> findByUserLine2(String userLine2);
}