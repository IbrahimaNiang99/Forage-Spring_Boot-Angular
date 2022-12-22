package com.senforage.backforage.Service;

import com.senforage.backforage.Entity.User;
import com.senforage.backforage.Entity.Role;

import java.util.List;


public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String userName, String roleName);
    User getUser(String userName);
    List<User> getUsers();

}
