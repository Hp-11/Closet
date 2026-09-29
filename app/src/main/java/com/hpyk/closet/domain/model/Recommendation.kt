package com.hpyk.closet.domain.model

data class Recommendation(
    val item: WardrobeItem,
    val score: Int,
    val reasons: List<String> = emptyList()
)