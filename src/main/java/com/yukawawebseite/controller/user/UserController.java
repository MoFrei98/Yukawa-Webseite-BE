package com.yukawawebseite.controller.user;

import com.yukawawebseite.configuration.jwt.AuthResponse;
import com.yukawawebseite.configuration.jwt.JwtUtil;
import com.yukawawebseite.models.user.User;
import com.yukawawebseite.repositories.user.UserRepository;
import com.yukawawebseite.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get-all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<User> getUserById(@PathVariable String uuid) {
        Optional<User> user = userService.getUserById(uuid);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/has-role/{uuid}/{roleName}")
    public ResponseEntity<Boolean> hasUserRole(@PathVariable String uuid, @PathVariable String roleName) {
        try {
            boolean hasRole = userRepository.userHasRole(uuid, roleName);
            return ResponseEntity.ok(hasRole);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User loggedInUser = userService.login(user.getUsername(), user.getPassword());
            if (loggedInUser != null) {
                String token = JwtUtil.generateToken(loggedInUser.getUsername());
                return ResponseEntity.ok(new AuthResponse(token));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Login failed"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Exception: " + e.getMessage()));
        }
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
