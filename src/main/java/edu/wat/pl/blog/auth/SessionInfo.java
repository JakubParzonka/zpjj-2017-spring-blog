/*
package edu.wat.pl.blog.auth;

import edu.wat.pl.blog.user.model.User;
import edu.wat.pl.blog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SessionInfo {

    @Autowired
    private UserService userService;


    private User user;

    protected User getCurrentUser() {
        if (user == null) {
            user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return user;
    }
    public String getCurrentUsername(){
        return getCurrentUser().getUsername();
    }
}*/
