package com.example.kursach.service;

import com.example.kursach.domain.Cart;
import com.example.kursach.domain.Dish;
import com.example.kursach.repository.CartRepo;
import com.example.kursach.repository.DishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserService userService;
    @Autowired
    private DishRepo dishRepo;

    public Iterable<Dish> showAll(){
        return dishRepo.findAll();
    }
    //public Iterable<Dish> getAllDish(){ return dishRepo.findAll();}
    public Optional<Dish> getProductById(long id) {
        return dishRepo.findById(id);
    }



    public List<Cart> cart(){

        List<Cart> cards=cartRepo.findByUser(userService.currentUser());
        products(cards);

        return cards;
    }

    public List<Dish> products(List<Cart> cards){
        List<Dish> products = new LinkedList<>();
        for(Cart s: cards){
            products.add(s.getDish());
        }

        return products;
    }

    private Cart checkProductInCard(Dish dish){
        List<Cart> cards=cartRepo.findByUser(userService.currentUser());
        for(Cart s: cards){
            if(s.getDish().equals(dish)){
                return s;
            }
        }
        return null;
    }


}
