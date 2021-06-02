package com.recipenut.recipenut.controllers;

import com.recipenut.recipenut.Models.Recipe;
import com.recipenut.recipenut.Models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class DisplayRecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("displayRecipe/{recipeId}")
    public String displayRecipe(Model model, @PathVariable int recipeId) {

        Optional optRecipe = recipeRepository.findById(recipeId);
        if(optRecipe.isPresent()) {
            Recipe recipe = (Recipe) optRecipe.get();
            model.addAttribute("recipe", recipe);
            return "displayRecipe";
        } else {
            return "index";
        }
    }
}
