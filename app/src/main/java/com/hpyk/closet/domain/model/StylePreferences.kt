package com.hpyk.closet.domain.model

data class StylePreferences(
    val occasion: String = "Casual",
    val weather: String = "Warm",
    val mood: String = "Relaxed",
    val color: String = "Any color"
)