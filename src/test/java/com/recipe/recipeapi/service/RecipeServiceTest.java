package com.recipe.recipeapi.service;

import com.recipe.recipeapi.model.Recipe;
import com.recipe.recipeapi.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    public RecipeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRecipes() {
        Recipe recipe1 = new Recipe();
        recipe1.setTitle("Pizza");

        Recipe recipe2 = new Recipe();
        recipe2.setTitle("Salad");

        when(recipeRepository.findAll()).thenReturn(Arrays.asList(recipe1, recipe2));

        List<Recipe> result = recipeService.getAllRecipes();
        assertEquals(2, result.size());
        verify(recipeRepository, times(1)).findAll();
    }
}
