package com.recipenut.recipenut.controllers;

import com.recipenut.recipenut.Models.Recipe;
import com.recipenut.recipenut.Models.User;
import com.recipenut.recipenut.Models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class PostRecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("index")
    String displayAllRecipes(Model model) {
        model.addAttribute("title", "All Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "index";
    }

    @GetMapping("/postRecipe")
    public String getPostRecipe(Model model) {
        model.addAttribute("title", "Post a Recipe");
        model.addAttribute("recipe", new Recipe());
        return "postRecipe";
    }

    @PostMapping("/postRecipe")
    public String addRecipe(@ModelAttribute @Valid Recipe recipe, Errors errors, Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Post a Recipe");
            model.addAttribute("errors", errors);
            model.addAttribute(new Recipe());
            return "postRecipe";
        }
        System.out.println(recipe.getIngredientList());
        System.out.println(recipe.getCategory());
        System.out.println(recipe.getName());
        recipeRepository.save(recipe);
        return "/index";
    }


//    @PostMapping("registration")
//    public String register(@ModelAttribute User user, Error errors, Model model){
//        if(errors.hasErrors()) {
//            model.addAttribute("title")
//        }

}
