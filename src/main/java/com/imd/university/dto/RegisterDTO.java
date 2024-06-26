package com.imd.university.dto;

import com.imd.university.model.UserRoles;

public record RegisterDTO(
    String login,
    String password,
    UserRoles role
) {
    
}
