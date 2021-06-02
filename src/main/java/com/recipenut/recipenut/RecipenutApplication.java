package com.recipenut.recipenut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class RecipenutApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipenutApplication.class, args);
		ArrayList<String> Categories = new ArrayList<String>();
		Categories.add("Vegetarian");
		Categories.add("Vegan");
		Categories.add("Gluten-Free");
		Categories.add("Paleo");
		Categories.add("Starters");
		Categories.add("Main Courses");
		Categories.add("Sides");
		Categories.add("Deserts");
	}

}
