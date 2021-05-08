package com.example.kursach.repository;

import com.example.kursach.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DishRepo extends CrudRepository<Dish, Long> {

    List<Dish> findByName(String name);




}
