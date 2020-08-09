package com.example.pet1.service;

import com.example.pet1.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends BaseService<User>, UserDetailsService {
    User findByUsername(String username);
    User findByEmail(String email);

}
