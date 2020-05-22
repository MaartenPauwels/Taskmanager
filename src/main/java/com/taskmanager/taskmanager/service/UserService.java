package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.domain.User;
import com.taskmanager.taskmanager.domain.UserRole;
import com.taskmanager.taskmanager.dto.CreateUserDTO;
import com.taskmanager.taskmanager.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


public interface UserService extends UserDetailsService {
    UserDTO createUser(CreateUserDTO userDTO);

}

