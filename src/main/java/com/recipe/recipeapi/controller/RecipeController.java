package com.recipe.recipeapi.controller;

import com.recipe.recipeapi.model.Recipe;
import com.recipe.recipeapi.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    // GET /recipes - Fetch all recipes
    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    // GET /recipes/paged - Fetch recipes with pagination support
    @GetMapping("/paged")
    public Page<Recipe> getPaginatedRecipes(Pageable pageable) {
        return recipeService.getRecipesPaginated(pageable);
    }

    // GET /recipes/{id} - Fetch a recipe by ID
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable String id) {
        return recipeService.getRecipeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /recipes - Create a new recipe
    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe savedRecipe = recipeService.saveRecipe(recipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe);
    }

    // PUT /recipes/{id} - Update an existing recipe
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable String id, @Valid @RequestBody Recipe updatedRecipe) {
        return recipeService.getRecipeById(id)
                .map(existing -> {
                    updatedRecipe.setId(id); // Preserve original ID
                    return ResponseEntity.ok(recipeService.saveRecipe(updatedRecipe));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /recipes/{id} - Delete a recipe by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable String id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    // GET /recipes/search?title=... - Search recipes by title (partial match)
    @GetMapping("/search")
    public List<Recipe> searchByTitle(@RequestParam String title) {
        return recipeService.searchByTitle(title);
    }

    // GET /recipes/filter?category=... - Filter recipes by category
    @GetMapping("/filter")
    public List<Recipe> filterByCategory(@RequestParam String category) {
        return recipeService.filterByCategory(category);
    }
}
