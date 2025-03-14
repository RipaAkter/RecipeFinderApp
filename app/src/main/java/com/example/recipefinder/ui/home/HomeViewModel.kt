package com.example.recipefinder.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.data.model.Recipe
import com.example.recipefinder.data.repository.recipe.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class HomeState {
    object Idle : HomeState()
    object Loading : HomeState()
    data class Success(val recipes: List<Recipe>) : HomeState()
    data class Error(val message: String) : HomeState()
}

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
) : ViewModel() {

    val homeState: StateFlow<HomeState> =
        recipeRepository.getRandomRecipes()
            .map { recipes: List<Recipe> ->
                HomeState.Success(recipes)
            }
            .catch { HomeState.Error(it.message.toString()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = HomeState.Idle
            )

    suspend fun search(
        searchType: String,
        query: String,
        time: Int,
        mealType: String
    ): List<Recipe> {
        return if (searchType == "Ingredient") {
            searchByIngredients(query = query, time = time)
        } else {
            searchByMealType(query = query, time = time, mealType = mealType)
        }
    }

    private suspend fun searchByIngredients(query: String, time: Int): List<Recipe> {
        return try {
            val sanitizedQuery =
                if (query.contains(",")) {
                    query.trim().replace(" ", "")
                } else {
                    query.trim()
                }
            val result: List<Recipe> = recipeRepository.searchRecipesByIngredients(
                maxReadyTime = time,
                ingredients = sanitizedQuery
            )
            result
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList<Recipe>()
        }
    }

    private suspend fun searchByMealType(
        query: String,
        time: Int,
        mealType: String
    ): List<Recipe> {
        return try {
            val sanitizedQuery = if (query.contains(",")) {
                query.trim().replace(" ", "")
            } else {
                query.trim()
            }
            val result: List<Recipe> = recipeRepository.searchMealType(
                query = sanitizedQuery,
                type = mealType,
                maxReadyTime = time,
                ingredients = sanitizedQuery
            )
            result
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList<Recipe>()
        }
    }

    // returns like count of a recipe
    suspend fun getRecipeLikeCount(recipeId: Int): Int {
        return try {
            recipeRepository.getLikesForRecipes(recipeId)
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }

    fun save(recipe: Recipe) {
        viewModelScope.launch {
            recipeRepository.save(recipe)
        }
    }
}
