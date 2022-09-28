package com.cloud.product.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection="product")
public class Product {
    @Id
    private String id;
    private String productLine1;
    private String productLine2;
}
