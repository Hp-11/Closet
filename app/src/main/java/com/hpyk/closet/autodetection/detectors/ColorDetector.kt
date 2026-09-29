
package com.hpyk.closet.autodetection.detectors

import android.graphics.Bitmap
import android.graphics.Color

class ColorDetector {

    fun detect(bitmap: Bitmap): String {

        if (bitmap.width == 0 || bitmap.height == 0) {
            return "#808080"
        }

        var red = 0L
        var green = 0L
        var blue = 0L
        var samples = 0

        val stepX = maxOf(1, bitmap.width / 50)
        val stepY = maxOf(1, bitmap.height / 50)

        for (x in 0 until bitmap.width step stepX) {

            for (y in 0 until bitmap.height step stepY) {

                val pixel = bitmap.getPixel(x, y)

                red += Color.red(pixel)
                green += Color.green(pixel)
                blue += Color.blue(pixel)

                samples++
            }
        }

        if (samples == 0) {
            return "#808080"
        }

        val averageRed =
            (red / samples).toInt()

        val averageGreen =
            (green / samples).toInt()

        val averageBlue =
            (blue / samples).toInt()

        return String.format(
            "#%02X%02X%02X",
            averageRed,
            averageGreen,
            averageBlue
        )
    }
}
