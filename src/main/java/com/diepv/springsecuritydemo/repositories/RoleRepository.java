package com.diepv.springsecuritydemo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diepv.springsecuritydemo.models.ERole;
import com.diepv.springsecuritydemo.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);
}
