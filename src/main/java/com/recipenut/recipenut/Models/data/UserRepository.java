package com.recipenut.recipenut.Models.data;

import com.recipenut.recipenut.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
