package com.example.kursach.global;

import com.example.kursach.domain.Dish;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Dish> cart;
    static {
        cart = new ArrayList<Dish>();
    }
}
