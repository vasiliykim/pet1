package com.example.pet1.controller;

import com.example.pet1.enumiration.RoleEnum;
import com.example.pet1.model.User;
import com.example.pet1.service.RoleService;
import com.example.pet1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("roles", RoleEnum.values());
        return "registration";
    }



    @PostMapping("/registration")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String passwordConfirm,
                          @RequestParam String firstname,
                          @RequestParam String lastname,
                          @RequestParam RoleEnum role,
                          Model model) {


        if (!password.equals(passwordConfirm)){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        User user = new User();
        user.setPassword(password);
        user.setActive(true);
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.addRole(roleService.findByName(role.toString()));
        if (userService.findByUsername(username) != null){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }else {
            userService.saveOrUpdate(user);
        }

        return "index";
    }
}