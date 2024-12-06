package com.example.recipefinder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipefinder.ui.home.HomeContent
import com.example.recipefinder.ui.recipedetails.RecipeDetailsScreen


@Composable
fun RecipeFinderNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = RecipeFinderDestinations.HOME_ROUTE,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        val navigationActions = RecipeFinderNavigation(navController)

        composable(
            route = RecipeFinderDestinations.HOME_ROUTE
        ) {
            HomeContent(
                { navigationActions.navigateToRecipeDetailsScreen(it) }
            )
        }

        composable(
            route = "${RecipeFinderDestinations.RECIPE_DETAILS_ROUTE}/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId")
            if (recipeId!= null) {
                RecipeDetailsScreen(
                    recipeId = recipeId,
                    onPopCurrent = { navigationActions.popCurrentDestination() },
                )
            }
        }
    }
}
