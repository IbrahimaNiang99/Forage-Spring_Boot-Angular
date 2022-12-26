package com.example.jwt.Service;
import com.example.jwt.Entity.Role;
import com.example.jwt.Entity.User;

import java.util.List;


public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String userName, String roleName);
    User getUser(String userName);
    List<User> getUsers();

}
