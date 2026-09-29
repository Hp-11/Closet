package com.hpyk.closet.presentation.recommendation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.hpyk.closet.ClosetApplication
import com.hpyk.closet.core.recommendation.RecommendationEngine
import com.hpyk.closet.domain.model.WardrobeItem
import com.hpyk.closet.domain.usecase.GetRecommendationsUseCase

class RecommendationViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository =
        (application as ClosetApplication)
            .repository

    private val getRecommendations =
        GetRecommendationsUseCase(
            RecommendationEngine()
        )

    var uiState =
        androidx.compose.runtime.mutableStateOf(
            RecommendationUiState()
        )
        private set

    val wardrobe =
        repository.wardrobe

    fun setOccasion(
        value: String
    ) {

        uiState.value =
            uiState.value.copy(
                occasion = value
            )
    }

    fun setWeather(
        value: String
    ) {

        uiState.value =
            uiState.value.copy(
                weather = value
            )
    }

    fun setMood(
        value: String
    ) {

        uiState.value =
            uiState.value.copy(
                mood = value
            )
    }

    fun setColor(
        value: String
    ) {

        uiState.value =
            uiState.value.copy(
                color = value
            )
    }

    fun recommend(
        items: List<WardrobeItem>
    ) {

        val state =
            uiState.value

        val result =
            getRecommendations(
                items = items,
                occasion = state.occasion,
                weather = state.weather,
                mood = state.mood,
                color = state.color
            )

        uiState.value =
            state.copy(
                recommendations = result
            )
    }
}