package com.example.socialmediabackenddemo.Model.Services.Database;

import com.example.socialmediabackenddemo.Model.Business.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByRoleName(String roleName);
}
