package com.yukawawebseite.repositories.user;

import com.yukawawebseite.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);

    Optional<User> findByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.uuid = :uuid AND u.role.name = :roleName")
    boolean userHasRole(@Param("uuid") String uuid, @Param("roleName") String roleName);
}
