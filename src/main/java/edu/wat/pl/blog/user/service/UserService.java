package edu.wat.pl.blog.user.service;

import edu.wat.pl.blog.user.model.User;
import edu.wat.pl.blog.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User newUser) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.setUsername(newUser.getUsername());
        newUser.setEnabled(true);
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
//        user.setEmail("parzon@parzon.pl");
//        newUser.addRole("ROLE_USER");
        newUser.addRole("ROLE_ADMIN");

//        newUser.setRole(new Role(RoleEnum.USER.toString()));
        userRepository.save(newUser);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
