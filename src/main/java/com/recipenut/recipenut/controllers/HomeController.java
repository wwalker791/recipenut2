package com.recipenut.recipenut.controllers;

import com.recipenut.recipenut.Models.Recipe;
import com.recipenut.recipenut.Models.User;
import com.recipenut.recipenut.Models.data.RecipeRepository;
import com.recipenut.recipenut.Models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private RecipeRepository recipeRepository;

//    @GetMapping("/results")
//    public  List<Recipe> findAll(@RequestParam String keywords) {
//        return (List<Recipe>) recipeRepository.findByKeywords(keywords);
//    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    String displayAllRecipes(Model model) {
        model.addAttribute("title", "All Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "/index";
    }

//    @RequestMapping("index")
//    public String search(Model model) {
//        model.addAttribute("recipes", "Search Recipes");
//        return "search";
//    }

    @GetMapping("/search")
    public String search(@Param("searchTerm") String searchTerm, Model model) {
        System.out.println(searchTerm);
        model.addAttribute("keyword", searchTerm);
        model.addAttribute("title", "Search results for" + searchTerm);
        return "results";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchTerm) {
        Iterable<Recipe> recipes;
        if(searchTerm.toLowerCase().equals("")){
            recipes = recipeRepository.findAll();
        } else {
           recipes = Recipe.findBySearchTerm(searchTerm, recipeRepository.findAll());
           model.addAttribute("title", "Recipes matching your criteria");

        }
        model.addAttribute("recipes", recipes);
        return "search";
    }

    @GetMapping("profile")
    public String displayProfile(Model model, @RequestParam User user) {
        return "profile";
    }

}
