//package com.recipenut.recipenut.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//
//@Controller
//public class LoginController {
//
//    @NotBlank(message = "Username is required")
//    private String username;
//
//    @NotBlank(message = "Password is required")
//    private String password;
//
//    @GetMapping("login")
//    @ResponseBody
//    public String loginForm() {
//        String html =
//                "<html>" +
//                "<body>" +
//                "<form>" +
//                "<input type= 'text' name = 'username'>" +
//                "<input type= 'text' name = 'password'>" +
//                "<input type= 'submit' value = 'Submit'>" +
//                "</form>"+
//                "</body>"+
//                "</html>";
//        return html;
//    }
//
//
//}
