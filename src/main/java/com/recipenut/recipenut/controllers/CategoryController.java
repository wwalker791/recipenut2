package com.recipenut.recipenut.controllers;

import com.recipenut.recipenut.Models.Recipe;
import com.recipenut.recipenut.Models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping(value = "view-category/{category}")
    public String displayCategory(Model model, @PathVariable String category) {
        Iterable<Recipe> someRecipes;
        someRecipes = Recipe.findByCategory(recipeRepository.findAll(), category);
        model.addAttribute("title", "Recipes fitting" + category );
        model.addAttribute("someRecipes", someRecipes);
        return "/view-category/{category}";
    }

}
