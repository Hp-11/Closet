package com.hpyk.closet.core.constants

object ClosetConstants {

    const val MAX_WARDROBE_ITEMS = 100

    const val PREFS_NAME = "wardrobe_metadata"

    const val IMAGE_DIRECTORY = "wardrobe_images"

    val occasions = listOf(
        "Casual",
        "Ethnic",
        "Party",
        "Formal",
        "Smart Casual",
        "Sports",
        "Travel"
    )

    val categories = listOf(
        "Shirt",
        "T-shirt",
        "Dress",
        "Trousers",
        "Jeans",
        "Skirt",
        "Jacket",
        "Footwear",
        "Accessories"
    )

    val weatherOptions = listOf(
        "Warm",
        "Hot",
        "Cool",
        "Rainy"
    )

    val moodOptions = listOf(
        "Relaxed",
        "Confident",
        "Happy",
        "Elegant",
        "Energetic"
    )

    val moodColors = mapOf(
        "Relaxed" to listOf(
            "blue",
            "green",
            "white",
            "beige"
        ),
        "Confident" to listOf(
            "black",
            "red",
            "navy"
        ),
        "Happy" to listOf(
            "yellow",
            "orange",
            "pink",
            "red"
        ),
        "Elegant" to listOf(
            "black",
            "white",
            "navy",
            "burgundy"
        ),
        "Energetic" to listOf(
            "red",
            "orange",
            "yellow",
            "blue"
        )
    )

    val weatherCategories = mapOf(
        "Hot" to listOf(
            "T-shirt",
            "Dress",
            "Skirt"
        ),
        "Cool" to listOf(
            "Jacket",
            "Jeans",
            "Trousers"
        ),
        "Rainy" to listOf(
            "Jacket",
            "Footwear",
            "Trousers"
        ),
        "Warm" to emptyList()
    )
}