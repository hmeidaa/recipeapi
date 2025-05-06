package com.recipe.recipeapi.repository;

import com.recipe.recipeapi.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Repository interface for CRUD operations on Recipe documents in MongoDB.
 */
@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

    @Override
    @NonNull
    Page<Recipe> findAll(@NonNull Pageable pageable);

    List<Recipe> findByTitleContainingIgnoreCase(String title);

    List<Recipe> findByCategoryIgnoreCase(String category);
}
