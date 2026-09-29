
package com.hpyk.closet.autodetection

data class AutoDetectionResult(
    val color: String? = null,
    val clothingType: String? = null,
    val category: String? = null,
    val occasion: String? = null,
    val weatherCompatibility: String? = null,
    val mood: String? = null,

    val colorConfidence: Float = 0f,
    val clothingTypeConfidence: Float = 0f,
    val categoryConfidence: Float = 0f,
    val occasionConfidence: Float = 0f,
    val weatherConfidence: Float = 0f,
    val moodConfidence: Float = 0f
)

