package com.example.yukawawebseitebe.services.user;

import com.example.yukawawebseitebe.models.user.User;
import com.example.yukawawebseitebe.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String uuid) {
        return userRepository.findById(uuid);
    }

    public User saveUser(User user) {
        Optional<User> optionalUser = userRepository.findByFirstNameAndLastNameAndEmail(user.getFirstName(), user.getLastName(), user.getEmail());
        if (optionalUser.isPresent())
            return null;
        else
            return userRepository.saveAndFlush(user);
    }

    public User updateUser(User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(updatedUser.uuid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRole(updatedUser.getRole());
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setDateLastLogin(updatedUser.getDateLastLogin());
            return userRepository.saveAndFlush(user);
        } else
            return null;
    }

    public void deleteUser(String uuid) {
        userRepository.deleteById(uuid);
    }

}
