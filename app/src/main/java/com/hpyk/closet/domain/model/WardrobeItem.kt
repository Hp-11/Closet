package com.hpyk.closet.domain.model

data class WardrobeItem(
    val id: Long,
    val imagePath: String,
    val gender: String,
    val category: String,
    val color: String,
    val occasion: String,
    val weatherCompatibility: List<String> = emptyList(),
    val moodCompatibility: List<String> = emptyList()
)