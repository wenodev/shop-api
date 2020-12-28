package com.weno.shop.config;

import com.weno.shop.entity.Role;
import com.weno.shop.entity.RoleName;
import com.weno.shop.entity.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InitDataConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        //Roles 초기화
        //h2-database 사용시 필요
        Role userRole = Role.builder().name(RoleName.ROLE_USER).build();
        Role adminRole = Role.builder().name(RoleName.ROLE_ADMIN).build();

        roleRepository.save(userRole);
        roleRepository.save(adminRole);

    }
}
