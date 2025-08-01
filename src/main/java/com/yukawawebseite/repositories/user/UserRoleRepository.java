package com.yukawawebseite.repositories.user;

import com.yukawawebseite.models.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    boolean existsByName(String name);

    Optional<UserRole> findByName(String defaultUser);
}