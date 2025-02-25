package com.yukawawebseite.controller.user;

import com.yukawawebseite.configuration.jwt.AuthResponse;
import com.yukawawebseite.configuration.jwt.JwtUtil;
import com.yukawawebseite.models.user.User;
import com.yukawawebseite.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<User> getUserById(@PathVariable String uuid) {
        Optional<User> user = userService.getUserById(uuid);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Optional<User> userOptional = userService.login(user.getUsername(), user.getPassword());
        if (userOptional.isPresent()) {
            User loggedInUser = userOptional.get();
            String token = JwtUtil.generateToken(loggedInUser.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.register(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName());
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("delete/{uuid}")
    public ResponseEntity<Void> deleteUser(@PathVariable String uuid) {
        userService.deleteUser(uuid);
        return ResponseEntity.noContent().build();
    }
}
