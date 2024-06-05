package com.springsecurity.repository;

import com.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);

    Optional<User> findByRoles_RoleName(String userName);

}
