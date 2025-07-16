package com.yukawawebseite.services.user;

import com.yukawawebseite.models.user.User;
import com.yukawawebseite.models.user.UserRole;
import com.yukawawebseite.repositories.user.UserRepository;
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

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String uuid) {
        return userRepository.findById(uuid);
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
            user.setRole(updatedUser.getRole());
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setLastLogin(updatedUser.getLastLogin());
            return userRepository.saveAndFlush(user);
        } else
            return null;
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
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.saveAndFlush(user);
    }
}
