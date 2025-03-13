package com.example.recipefinder.ui.onboarding

import androidx.lifecycle.ViewModel
import com.example.recipefinder.datastore.RecipeDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val dataStore: RecipeDataStore
) : ViewModel() {
    fun getOnBoardingItemsList() = OnBoardingProvider.onBoardingItems
}
