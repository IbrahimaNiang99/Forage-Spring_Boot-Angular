package com.senforage.backforage.Repository;

import com.senforage.backforage.Entity.Role
;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
