package com.example.socialmediabackenddemo.Model.Services.Common;

import com.example.socialmediabackenddemo.Model.Business.Role;
import com.example.socialmediabackenddemo.Model.Services.Database.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleService {
    @Autowired
    RoleRepository roleRepository ;

    public Role findRoleByName(String roleName){
        return roleRepository.findRoleByRoleName(roleName);
    }
}
