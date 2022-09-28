package com.cloud.user.repository;

import com.cloud.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void createUser(User user) {
        mongoTemplate.save(user);
    }
}