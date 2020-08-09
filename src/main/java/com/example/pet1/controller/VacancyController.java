package com.example.pet1.controller;

import com.example.pet1.model.Vacancy;
import com.example.pet1.service.UserService;
import com.example.pet1.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;
    @Autowired
    private UserService  userService;

    @GetMapping("/vacancy")
    public String allVacancy(Model model) {


        List<Vacancy> vacancyList = vacancyService.findAll();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("vacancyList", vacancyList);
        attributes.put("currentUser", userService.findCurrentUser());
        model.addAllAttributes(attributes);
        return "vacancy";
    }

    @GetMapping("/vacancy/{id}")
    public String vacancy(@PathVariable Long id, Model model){
        System.out.println("vacancyId = " + id);
        Vacancy vacancy = vacancyService.findById(id);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("vacancy", vacancy);
        model.addAllAttributes(attributes);
        return "vacancy/view";
    }

    @PostMapping("/vacancy")
    public String addVacancy(@RequestParam String description,
                             @RequestParam String salary,
                             Model model){


        Vacancy vacancy = new Vacancy(description, Integer.valueOf(salary), userService.findCurrentUser());

        vacancyService.saveOrUpdate(vacancy);
        return allVacancy(model);
    }

}
