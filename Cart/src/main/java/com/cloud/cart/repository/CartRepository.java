package com.cloud.cart.repository;

import com.cloud.cart.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    List<Cart> findByCartLine1AndCartLine2(String cartLine1, String cartLine2);
    List<Cart> findByCartLine1(String cartLine1);
    List<Cart> findByCartLine2(String cartLine2);
}