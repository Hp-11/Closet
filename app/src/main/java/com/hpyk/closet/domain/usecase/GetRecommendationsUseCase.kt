package com.hpyk.closet.domain.usecase

import com.hpyk.closet.core.recommendation.RecommendationEngine
import com.hpyk.closet.domain.model.WardrobeItem

class GetRecommendationsUseCase(
    private val recommendationEngine: RecommendationEngine
) {

    operator fun invoke(
        items: List<WardrobeItem>,
        occasion: String,
        weather: String,
        mood: String,
        color: String
    ): List<WardrobeItem> {

        return recommendationEngine.recommend(
            items = items,
            occasion = occasion,
            weather = weather,
            mood = mood,
            color = color
        )
    }
}