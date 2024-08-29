package com.Reis.Api.Auth.controller;

import com.Reis.Api.Auth.Dtos.AuthenticationDto;
import com.Reis.Api.Auth.Dtos.LoginResponse;
import com.Reis.Api.Auth.Dtos.UserDTO;
import com.Reis.Api.Auth.domain.User;
import com.Reis.Api.Auth.infra.config.TokenService;
import com.Reis.Api.Auth.repository.UserRepository;
import com.Reis.Api.Auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository repository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody  AuthenticationDto dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User)auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> insertUser(@RequestBody  UserDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.insertUser(dto));
    }
}
