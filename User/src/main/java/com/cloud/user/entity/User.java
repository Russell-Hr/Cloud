package com.cloud.user.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection="user")
public class User {
    @Id
    private String id;
    private String userLine1;
    private String userLine2;
}
