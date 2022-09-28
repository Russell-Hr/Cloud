package com.cloud.cart.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Data
@Document(collection="cart")
public class Cart {
    @Id
    private String id;
    private String cartLine1;
    private String cartLine2;
}
