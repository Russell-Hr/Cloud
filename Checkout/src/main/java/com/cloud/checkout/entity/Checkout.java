package com.cloud.checkout.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Data
@Document(collection="checkout")
public class Checkout {
    @Id
    private String id;
    private String checkoutLine1;
    private String checkoutLine2;
}
