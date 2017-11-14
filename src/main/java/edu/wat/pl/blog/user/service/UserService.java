package edu.wat.pl.blog.user.service;

import edu.wat.pl.blog.role.RoleEnum;
import edu.wat.pl.blog.role.model.Role;
import edu.wat.pl.blog.user.model.User;
import edu.wat.pl.blog.user.repository.UserRepository;
import edu.wat.pl.blog.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User newUser) {
        newUser.setUserSignUpDate(TimeUtils.getCurrentTime());
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.setRole(new Role(RoleEnum.USER.toString()));
        userRepository.save(newUser);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
