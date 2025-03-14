package com.example.recipefinder.data.repository.recipe

import com.example.recipefinder.data.model.Recipe
import com.example.recipefinder.data.model.RecipeAnalyzedInstructions
import com.example.recipefinder.data.model.RecipeNutrient
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRandomRecipes(): Flow<List<Recipe>>
    suspend fun getRecipeDetail(recipeId: Int): Recipe?
    suspend fun searchRecipesByIngredients(maxReadyTime: Int, ingredients: String): List<Recipe>
    suspend fun getAnalyzedInstructions(recipeId: Int): RecipeAnalyzedInstructions
    suspend fun addNewRecipe(recipe: Recipe)
    suspend fun getNutrients(recipeId: Int): RecipeNutrient
    suspend fun searchMealType(
        query: String,
        type: String,
        maxReadyTime: Int,
        ingredients: String
    ): List<Recipe>

    suspend fun sendTip(recipeId: Int, tip: String)
    suspend fun like(recipeId: Int)
    suspend fun getLikesForRecipes(recipeId: Int): Int
    suspend fun save(recipe: Recipe)
}
