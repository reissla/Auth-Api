package com.Reis.Api.Auth.Dtos;

import com.Reis.Api.Auth.domain.Role;

public record UserDTO(String id,String login, String password, Role role) {
}
