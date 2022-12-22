package com.senforage.backforage.Repository;

import com.senforage.backforage.Entity.Role
;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String roleName);
}
