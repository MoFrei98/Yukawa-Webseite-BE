package com.yukawawebseite.configuration;

import com.yukawawebseite.models.user.UserRole;
import com.yukawawebseite.repositories.user.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class DbConfig {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @PostConstruct
    public void databasePreset() {
        addRoles();
    }

    private void addRoles() {
        try {
            // ADMIN
            if (!userRoleRepository.existsByName("ADMIN")) {
                UserRole admin = new UserRole();
                admin.setName("ADMIN");
                admin.setCanPost(true);
                admin.setCanEdit(true);
                admin.setCanDelete(true);
                admin.setCanView(true);
                userRoleRepository.save(admin);
            }

            // DEFAULT_USER
            if (!userRoleRepository.existsByName("DEFAULT_USER")) {
                UserRole defUser = new UserRole();
                defUser.setName("DEFAULT_USER");
                defUser.setCanPost(false);
                defUser.setCanEdit(false);
                defUser.setCanDelete(false);
                defUser.setCanView(true);
                userRoleRepository.save(defUser);
            }
        } catch (Exception e) {
            System.err.println("Error during database preset: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
