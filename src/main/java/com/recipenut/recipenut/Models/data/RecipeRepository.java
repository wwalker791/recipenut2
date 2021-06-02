package com.recipenut.recipenut.Models.data;

import com.recipenut.recipenut.Models.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    Iterable<Recipe> findByKeywords(String searchTerm);
}
