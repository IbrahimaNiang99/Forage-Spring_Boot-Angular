package com.senforage.backforage.Services.Userservice;

import com.senforage.backforage.Entity.Role;
import com.senforage.backforage.Entity.User;

import java.util.List;

public interface UserService {

    User addUser(User user);
    Role addRole(Role role);
    void addRoleToUser(String userName, String roleName);
    User getUser(String userName);
    List<User> getUsers();
}
