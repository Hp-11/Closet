package com.hpyk.closet.core.recommendation

import com.hpyk.closet.core.constants.ClosetConstants
import com.hpyk.closet.domain.model.WardrobeItem

class RecommendationEngine {

    fun recommend(
        items: List<WardrobeItem>,
        occasion: String,
        weather: String,
        mood: String,
        color: String
    ): List<WardrobeItem> {

        return items
            .asSequence()

            .filter {
                it.occasion.equals(
                    occasion,
                    ignoreCase = true
                )
            }

            .filter {
                color == "Any color" ||
                        it.color.equals(
                            color,
                            ignoreCase = true
                        )
            }

            .sortedByDescending { item ->

                var score = 0

                val weatherCategories =
                    ClosetConstants
                        .weatherCategories[weather]
                        .orEmpty()

                val moodColors =
                    ClosetConstants
                        .moodColors[mood]
                        .orEmpty()

                if (item.category in weatherCategories) {
                    score += 3
                }

                if (
                    item.color.lowercase() in
                    moodColors.map { it.lowercase() }
                ) {
                    score += 2
                }

                if (color != "Any color") {
                    score += 4
                }

                score
            }

            .toList()
    }
}