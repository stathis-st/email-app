package com.emailapp.service;

import com.emailapp.domain.Role;
import com.emailapp.repository.RoleRepository;
import com.emailapp.repository.RoleRepositoryImpl;

import java.util.List;

public class RoleService {

    private RoleRepository roleRepository = new RoleRepositoryImpl();

    public List<Role> getAllRoles() {
        return roleRepository.getAll();
    }
}
