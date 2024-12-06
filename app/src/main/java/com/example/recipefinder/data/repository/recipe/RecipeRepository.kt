package com.example.recipefinder.data.repository.recipe

import com.example.recipefinder.data.model.Recipe
import com.example.recipefinder.data.model.RecipeAnalyzedInstructions
import com.example.recipefinder.data.model.RecipeNutrient
import com.example.recipefinder.data.model.SearchRecipeByIngredients
import com.example.recipefinder.model.SimilarRecipeItemVo
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRandomRecipes(): Flow<List<Recipe>?>
    suspend fun getSimilarRecipes(id: Int): List<SimilarRecipeItemVo>
    suspend fun getRecipeById(id: Int): Recipe?
    suspend fun searchRecipesByIngredients(ingredients: String): List<SearchRecipeByIngredients>
    suspend fun getAnalyzedInstructions(id: Int): RecipeAnalyzedInstructions
    suspend fun saveRecipeInformation(recipe: Recipe)
    suspend fun getNutrients(id: Int): RecipeNutrient
    suspend fun searchDishType(type: String): List<Recipe>
}
