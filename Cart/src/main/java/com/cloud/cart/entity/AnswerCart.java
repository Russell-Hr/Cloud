package com.cloud.cart.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection="answerCart")
public class AnswerCart {
    @Id
    private String id;
    private String answer;
}
