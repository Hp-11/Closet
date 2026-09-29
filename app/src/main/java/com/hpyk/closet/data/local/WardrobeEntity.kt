package com.hpyk.closet.data.local

data class WardrobeEntity(
    val id: Long,
    val imagePath: String,
    val gender: String,
    val category: String,
    val color: String,
    val occasion: String
)