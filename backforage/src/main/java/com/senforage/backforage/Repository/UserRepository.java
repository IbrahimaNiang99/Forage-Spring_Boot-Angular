package com.senforage.backforage.Repository;

import com.senforage.backforage.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
