package com.senforage.backforage.Services.Userservice;

import com.senforage.backforage.Entity.Role;
import com.senforage.backforage.Entity.User;
import com.senforage.backforage.Repository.RoleRepository;
import com.senforage.backforage.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImplement implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImplement(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = userRepository.findByUserName(userName);
        Role role = roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
