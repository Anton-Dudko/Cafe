package com.example.kursach.controller;


import com.example.kursach.domain.Dish;
import com.example.kursach.domain.User;
import com.example.kursach.repository.CartRepo;
import com.example.kursach.repository.DishRepo;
import com.example.kursach.service.DishService;
import com.example.kursach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
public class DishController {

    @Autowired
    private DishRepo dishRepo;

    @Autowired
    UserService userService;

    @Autowired
    CartRepo cartRepo;

    @Autowired
    DishService productsImp;

    @Value("${upload.path}")
    private String uploadPath;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addDishes")
    public String dishAdd() {
        return "dishAdd";
    }

    @PostMapping("/addDishes")
    public String add(@AuthenticationPrincipal User user, @RequestParam String name, @RequestParam String description, @RequestParam double weight,
            @RequestParam double cost,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Dish dish = new Dish(name, description, weight, cost, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            dish.setFilename(resultFilename);
        }

        dishRepo.save(dish);

        Iterable<Dish> dishes = dishRepo.findAll();

        model.put("dishes", dishes);

        return "main";
    }

    @GetMapping("/main/{id}")
    public String details(@PathVariable(value = "id") long id,
                          Model model) {

        Optional<Dish> dish = dishRepo.findById(id);
        ArrayList<Dish> res = new ArrayList<>();
        dish.ifPresent(res::add);
        model.addAttribute("dishes", res);
        return "dishDetails";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/main/{id}/remove")
    public String dishRemove(@PathVariable(value = "id") long id,
                           Model model) {
        Dish dish = dishRepo.findById(id).<RuntimeException>orElseThrow(() -> {
            throw new AssertionError();
        });
        dishRepo.deleteById(id);

        return "redirect:/main";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/main/{id}/edit")
    public String dishEdit(@PathVariable(value = "id") long id,
                           Model model) {

        Optional<Dish> dish = dishRepo.findById(id);
        ArrayList<Dish> res = new ArrayList<>();
        dish.ifPresent(res::add);

        model.addAttribute("dishes", res);
        return "dishEdit";
    }

    @PostMapping("/main/{id}/edit")
    public String dishUpdate(@PathVariable(value = "id") long id, @AuthenticationPrincipal User user, @RequestParam String name, @RequestParam String description, @RequestParam double weight,
                         @RequestParam double cost,
                         Model model,
                         @RequestParam("file") MultipartFile file
    ) throws IOException {

        Dish dish = dishRepo.findById(id).<RuntimeException>orElseThrow(() -> {
            throw new AssertionError();
        });
        dish.setName(name);
        dish.setDescription(description);
        dish.setWeight(weight);
        dish.setCost(cost);

        dishRepo.save(dish);


        return "redirect:/main";
    }


    @GetMapping("/card")
    public String showCard(Model model) {

        model.addAttribute("products",productsImp.products(productsImp.cart()));
        return "card";
    }



    @PostMapping("/card/{id}/delete")
    public  String deleteProductInCard(@PathVariable(value = "id") long id,Model model){

        cartRepo.delete(cartRepo.findByRemoveDishIdAndUser(id,userService.currentUser()));
        return "redirect:/card";

    }





}
