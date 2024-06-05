package com.springsecurity.repository;

import com.springsecurity.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByPermissionName(String permissionName);
    void deleteByPermissionName(String permissionName);

}
