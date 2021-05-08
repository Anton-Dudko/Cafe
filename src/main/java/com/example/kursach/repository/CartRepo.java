package com.example.kursach.repository;

import com.example.kursach.domain.Cart;
import com.example.kursach.domain.Dish;
import com.example.kursach.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo  extends JpaRepository<Cart,Long> {

    List<Cart> findByUser(User user);

    Cart findByUserAndDish(User user, Dish dish);

    Cart findByRemoveDishIdAndUser(Long product_id, User user);
}
