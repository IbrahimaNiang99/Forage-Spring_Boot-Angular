package com.example.jwt.Dto;

import lombok.Data;

@Data
public class UserRoleRequest {

    private String userName;
    private String roleName;
    private String password;

}
