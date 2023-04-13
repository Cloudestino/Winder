package com.example.pidev.Service;

import com.example.pidev.entity.Role;
import com.example.pidev.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceC {

    @Autowired
    RoleRepository roleRepository;
    public Role createNewRole(Role role) {

        return roleRepository.save(role);
    }


}