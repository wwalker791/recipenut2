package com.recipenut.recipenut.Models;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User extends AbstractEntity {

    @NotNull
    @Id @GeneratedValue
    private int id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters long.")
    private String username;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @Size(min= 8, max = 90, message = "Password must be between 8 and 30 characters long.")
    private String pwHash;

    @NotNull
    private String verifyPwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {
    }

    public User(String username, String email, String password, String verifyPassword) {
        this.username = username;
        this.email = email;
        this.pwHash = encoder.encode(password);
        this.verifyPwHash = encoder.encode(verifyPassword);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getId() {return id;
    }
}
