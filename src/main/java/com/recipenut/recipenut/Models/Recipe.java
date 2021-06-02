package com.recipenut.recipenut.Models;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.recipenut.recipenut.Models.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
public class Recipe {


    @Id
    @GeneratedValue
    private int recipeId;
    private User user;
    private String imageFile;
    @NotBlank
    @Size(min = 5, max = 50)
    private String name;
    @NotBlank
    private String ingredientList;
    @NotBlank
    private String preparation;
    @NotBlank
    private String category;
    @NotBlank
    private String keywords;

    public Recipe() {}

    public Recipe(int recipeId, String name, User user, String ingredientList, String preparation,
                  String keywords, String category, String imageFile) {
        this.recipeId = recipeId;
        this.name= name;
        this.user = user;
        this.ingredientList= ingredientList;
        this.preparation= preparation;
        this.keywords=keywords;
        this.category=category;
        this.imageFile=imageFile;
    }


    public static ArrayList<Recipe> findBySearchTerm(String searchTerm, Iterable<Recipe> allRecipes) {
        String lower_val = searchTerm.toLowerCase();

        ArrayList<Recipe> results = new ArrayList<>();


        for (Recipe recipe : allRecipes) {

            if (recipe.getName().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getUser().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getIngredientList().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getKeywords().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            } else if (recipe.getCategory().toString().toLowerCase().contains(lower_val)) {
                results.add(recipe);
            }

        }

        return results;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(String ingredientList) {
        this.ingredientList = ingredientList;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getRecipeId() {
        return recipeId;
    }
}
