package edu.wat.pl.blog.auth.service;

import edu.wat.pl.blog.role.RoleEnum;
import edu.wat.pl.blog.user.model.User;
import edu.wat.pl.blog.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DataLoader {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void initDatabase() {
        if (userRepository.findByUsername("parzon") == null) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setUsername("parzon");
            user.setPassword(passwordEncoder.encode("parzon"));
            user.setEnabled(true);
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.addRole(RoleEnum.ADMIN.name());
            user.addRole(RoleEnum.USER.name());
            userRepository.save(user);
        }
    }
}
