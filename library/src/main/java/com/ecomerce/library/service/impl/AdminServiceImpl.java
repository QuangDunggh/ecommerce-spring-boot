package com.ecomerce.library.service.impl;

import com.ecomerce.library.dto.AdminDto;
import com.ecomerce.library.model.Admin;
import com.ecomerce.library.repository.AdminRepository;
import com.ecomerce.library.repository.RoleRepository;
import com.ecomerce.library.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        admin.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
        return adminRepository.save(admin);
    }
}
