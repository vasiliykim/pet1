package com.example.pet1.controller;

import com.example.pet1.model.Vacancy;
import com.example.pet1.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @GetMapping("/vacancy")
    public String allVacancy(Model model) {


        List<Vacancy> vacancyList = vacancyService.findAll();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("vacancyList", vacancyList);
        model.addAllAttributes(attributes);
        return "vacancy";
    }

    @PostMapping("/vacancy")
    public String addVacancy(@RequestParam String description,
                             @RequestParam String salary,
                             Model model){

        Vacancy vacancy = new Vacancy(description, Integer.valueOf(salary));

        vacancyService.saveOrUpdate(vacancy);
        return allVacancy(model);
    }

}
