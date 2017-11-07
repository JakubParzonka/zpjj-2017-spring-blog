package edu.wat.pl.blog.auth.service;

import edu.wat.pl.blog.user.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
