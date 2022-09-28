package com.cloud.user.repository;

import com.cloud.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComplexUserRepository
        extends MongoRepository<User, String>, UserRepositoryCustom {
}
