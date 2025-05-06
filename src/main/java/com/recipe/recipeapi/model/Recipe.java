package com.recipe.recipeapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;

import java.util.List;

@Document(collection = "recipes")
public class Recipe {

    @Id
    private String id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Ingredients list is required")
    @Size(min = 1, message = "Ingredients must contain at least one item")
    private List<String> ingredients;

    @NotNull(message = "Instructions list is required")
    @Size(min = 1, message = "Instructions must contain at least one step")
    private List<String> instructions;

    @Min(value = 1, message = "Cooking time must be at least 1 minute")
    private int cookingTime;

    @NotBlank(message = "Category is required")
    private String category;

    public Recipe() {}

    public Recipe(String title, String description, List<String> ingredients,
                  List<String> instructions, int cookingTime, String category) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.cookingTime = cookingTime;
        this.category = category;
    }

    public Recipe(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }    

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getIngredients() { return ingredients; }
    public void setIngredients(List<String> ingredients) { this.ingredients = ingredients; }

    public List<String> getInstructions() { return instructions; }
    public void setInstructions(List<String> instructions) { this.instructions = instructions; }

    public int getCookingTime() { return cookingTime; }
    public void setCookingTime(int cookingTime) { this.cookingTime = cookingTime; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
