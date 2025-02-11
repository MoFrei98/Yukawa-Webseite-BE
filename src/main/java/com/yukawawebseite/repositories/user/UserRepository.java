package com.yukawawebseite.repositories.user;

import com.yukawawebseite.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
}
