package com.recipe.recipeapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.recipeapi.model.Recipe;
import com.recipe.recipeapi.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

  // Suppress deprecation warning from @MockBean (Spring Boot 3.2+)
        @SuppressWarnings("removal")
        @MockBean
        private RecipeService recipeService;


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser
    void testGetAllRecipes() throws Exception {
        Recipe r1 = new Recipe("1", "Pasta", "Delicious pasta");
        Recipe r2 = new Recipe("2", "Pizza", "Cheesy pizza");

        when(recipeService.getAllRecipes()).thenReturn(List.of(r1, r2));

        mockMvc.perform(get("/recipes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Pasta")))
                .andExpect(content().string(containsString("Pizza")));
    }

    @Test
    @WithMockUser
    void testCreateRecipe() throws Exception {
        Recipe recipe = new Recipe("Test Recipe", "Test Description",
                List.of("Ingredient 1"), List.of("Step 1"), 10, "Dessert");

        when(recipeService.saveRecipe(any(Recipe.class))).thenReturn(recipe);

        mockMvc.perform(post("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recipe)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    void testUpdateRecipe() throws Exception {
        String id = "123";
        Recipe updatedRecipe = new Recipe("Updated Title", "Updated Description",
                List.of("Updated Ingredient"), List.of("Updated Step"), 20, "Main");

        when(recipeService.getRecipeById(id)).thenReturn(java.util.Optional.of(updatedRecipe));
        when(recipeService.saveRecipe(any(Recipe.class))).thenReturn(updatedRecipe);

        mockMvc.perform(put("/recipes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRecipe)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void testDeleteRecipe() throws Exception {
        String id = "123";
        doNothing().when(recipeService).deleteRecipe(id);

        mockMvc.perform(delete("/recipes/{id}", id))
                .andExpect(status().isNoContent());
    }
}
