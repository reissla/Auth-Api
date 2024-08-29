package com.Reis.Api.Auth.services;

import com.Reis.Api.Auth.Dtos.UserDTO;
import com.Reis.Api.Auth.domain.Role;
import com.Reis.Api.Auth.domain.User;
import com.Reis.Api.Auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User insertUser(UserDTO dto){
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User user = new User(dto.id(), dto.login(),  encryptedPassword, Role.USER);
        userRepository.save(user);
        return user;
    }
}
