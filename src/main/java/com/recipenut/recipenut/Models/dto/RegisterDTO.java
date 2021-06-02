package com.recipenut.recipenut.Models.dto;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class RegisterDTO extends LoginDTO {


    @NotNull
    private String email;

    @NotNull
    private String verifyPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
