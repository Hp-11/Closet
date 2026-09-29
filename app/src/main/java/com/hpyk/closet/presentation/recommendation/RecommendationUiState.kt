package com.hpyk.closet.presentation.recommendation

import com.hpyk.closet.domain.model.WardrobeItem

data class RecommendationUiState(
    val occasion: String = "Casual",
    val weather: String = "Warm",
    val mood: String = "Relaxed",
    val color: String = "Any color",
    val recommendations: List<WardrobeItem>? = null
)