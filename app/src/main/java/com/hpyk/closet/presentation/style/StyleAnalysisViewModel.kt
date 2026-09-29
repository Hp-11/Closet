package com.hpyk.closet.presentation.style

import androidx.lifecycle.ViewModel
import com.hpyk.closet.domain.model.WardrobeItem

class StyleAnalysisViewModel : ViewModel() {

    fun occasionCount(
        items: List<WardrobeItem>,
        occasion: String
    ): Int {

        return items.count {
            it.occasion.equals(
                occasion,
                ignoreCase = true
            )
        }
    }

    fun colorCounts(
        items: List<WardrobeItem>
    ): Map<String, Int> {

        return items
            .groupingBy {
                it.color
                    .trim()
                    .replaceFirstChar { c ->
                        c.uppercase()
                    }
            }
            .eachCount()
    }

    fun weatherReadyCount(
        items: List<WardrobeItem>
    ): Int {

        val categories =
            listOf(
                "T-shirt",
                "Dress",
                "Skirt",
                "Jacket",
                "Jeans",
                "Trousers",
                "Footwear"
            )

        return items.count {
            it.category in categories
        }
    }

    fun moodReadyCount(
        items: List<WardrobeItem>
    ): Int {

        val colors =
            listOf(
                "blue",
                "green",
                "white",
                "beige",
                "black",
                "red",
                "navy",
                "yellow",
                "orange",
                "pink",
                "burgundy"
            )

        return items.count {
            it.color.lowercase() in colors
        }
    }
}