package edu.wat.pl.blog.user.model;


import edu.wat.pl.blog.role.model.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String username;

    private String password;

    private String passwordConfirm;

    private String email;

    private Date userSignUpDate;

    private Set<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getUserSignUpDate() {
        return userSignUpDate;
    }

    public void setUserSignUpDate(Date userSignUpDate) {
        this.userSignUpDate = userSignUpDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
