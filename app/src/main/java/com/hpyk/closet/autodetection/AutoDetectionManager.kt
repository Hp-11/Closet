
package com.hpyk.closet.autodetection

import android.graphics.Bitmap
import com.hpyk.closet.autodetection.detectors.CategoryDetector
import com.hpyk.closet.autodetection.detectors.ClothingTypeDetector
import com.hpyk.closet.autodetection.detectors.ColorDetector
import com.hpyk.closet.autodetection.detectors.MoodDetector
import com.hpyk.closet.autodetection.detectors.OccasionDetector
import com.hpyk.closet.autodetection.detectors.WeatherDetector

class AutoDetectionManager {

    private val colorDetector =
        ColorDetector()

    private val clothingTypeDetector =
        ClothingTypeDetector()

    private val categoryDetector =
        CategoryDetector()

    private val occasionDetector =
        OccasionDetector()

    private val weatherDetector =
        WeatherDetector()

    private val moodDetector =
        MoodDetector()

    fun analyze(
        bitmap: Bitmap
    ): AutoDetectionResult {

        val color =
            colorDetector.detect(bitmap)

        val clothingType =
            clothingTypeDetector.detect(bitmap)

        val category =
            categoryDetector.detect(bitmap)

        val occasion =
            occasionDetector.detect(bitmap)

        val weather =
            weatherDetector.detect(bitmap)

        val mood =
            moodDetector.detect(bitmap)

        return AutoDetectionResult(

            color = color,

            clothingType = clothingType,

            category = category,

            occasion = occasion,

            weatherCompatibility = weather,

            mood = mood,

            colorConfidence = 0.60f,

            clothingTypeConfidence = 0f,

            categoryConfidence = 0f,

            occasionConfidence = 0f,

            weatherConfidence = 0f,

            moodConfidence = 0f
        )
    }
}
