package com.example.kursach.controller;

import com.example.kursach.domain.Dish;
import com.example.kursach.domain.User;
import com.example.kursach.repository.DishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private DishRepo dishRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/location")
    public String location(Map<String, Object> model) {
        return "location";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter,
                       Model model) {
        Iterable<Dish> dishes = dishRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            dishes = dishRepo.findByName(filter);
        } else {
            dishes = dishRepo.findAll();
        }

        model.addAttribute("dishes", dishes);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double weight,
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



}
