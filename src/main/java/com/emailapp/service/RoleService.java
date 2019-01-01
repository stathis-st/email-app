package com.emailapp.service;

import com.emailapp.domain.Role;
import com.emailapp.repository.RoleRepository;
import com.emailapp.repository.RoleRepositoryImpl;

import java.util.List;

public class RoleService {

    private static RoleService roleServiceInstance;

    private RoleRepository roleRepository = RoleRepositoryImpl.getInstance();

    private RoleService() {
    }

    public static RoleService getInstance() {
        if (roleServiceInstance == null) {
            roleServiceInstance = new RoleService();
        }
        return roleServiceInstance;
    }

    public List<Role> getAllRoles() {
        return roleRepository.getAll();
    }
}
