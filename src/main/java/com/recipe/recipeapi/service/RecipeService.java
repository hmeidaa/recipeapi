package com.recipe.recipeapi.service;

import com.recipe.recipeapi.model.Recipe;
import com.recipe.recipeapi.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class that contains business logic for managing recipes.
 */
@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(String id) {
        return recipeRepository.findById(id);
    }

    // ✅ Unified save for both create and update
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(String id) {
        recipeRepository.deleteById(id);
    }

    public Page<Recipe> getRecipesPaginated(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    public List<Recipe> searchByTitle(String title) {
        return recipeRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Recipe> filterByCategory(String category) {
        return recipeRepository.findByCategoryIgnoreCase(category);
    }
}
