package com.yukawawebseite.services.user;

import com.yukawawebseite.models.user.User;
import com.yukawawebseite.models.user.UserRole;
import com.yukawawebseite.repositories.user.UserRepository;
import com.yukawawebseite.repositories.user.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String uuid) {
        return userRepository.findById(uuid);
    }

    public boolean userHasRole(String uuid, String roleName) {
        return userRepository.userHasRole(uuid, roleName);
    }

    /*
    public User saveUser(User user) {
        Optional<User> optionalUser = userRepository.findByFirstNameAndLastNameAndEmail(user.getFirstName(), user.getLastName(), user.getEmail());
        if (optionalUser.isPresent()) {
            User newUser = optionalUser.get();
            user.setRole(newUser.getRole());
            user.setCreatedAt(newUser.getCreatedAt());
            user.setLastLogin(newUser.getLastLogin());
            return userRepository.saveAndFlush(user);
        } else
            return null;
    }
     */

    public User updateUser(User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(updatedUser.uuid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (updatedUser.getRole() != null && !updatedUser.getRole().equals(user.getRole())) {
                user.setRole(updatedUser.getRole());
            }
            if (updatedUser.getFirstName() != null && !updatedUser.getFirstName().equals(user.getFirstName())) {
                user.setFirstName(updatedUser.getFirstName());
            }
            if (updatedUser.getLastName() != null && !updatedUser.getLastName().equals(user.getLastName())) {
                user.setLastName(updatedUser.getLastName());
            }
            if (updatedUser.getEmail() != null && !updatedUser.getEmail().equals(user.getEmail())) {
                user.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().equals(user.getPassword())) {
                user.setPassword(updatedUser.getPassword());
            }
            if (updatedUser.getLastLogin() != null && !updatedUser.getLastLogin().equals(user.getLastLogin())) {
                user.setLastLogin(updatedUser.getLastLogin());
            }

            return userRepository.saveAndFlush(user);
        } else {
            return null;
        }
    }

    public void deleteUser(String uuid) {
        userRepository.deleteById(uuid);
    }

    public User login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            User user = userOptional.get();
            user.setLastLogin(LocalDateTime.now());
            return userRepository.saveAndFlush(user);
        } else
            return null;
    }

    public User register(String username, String password, String firstname, String lastname) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setRole(userRoleRepository.findByName("DEFAULT_USER").orElseThrow(() -> new RuntimeException("User role not found")));
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.saveAndFlush(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
