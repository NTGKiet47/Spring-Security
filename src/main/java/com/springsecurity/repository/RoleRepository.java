package com.springsecurity.repository;

import com.springsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);

    Boolean existsByRoleName(String roleName);

    void deleteByRoleName(String roleName);
}
