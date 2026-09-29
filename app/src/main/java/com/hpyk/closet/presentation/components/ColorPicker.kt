package com.hpyk.closet.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
fun ColorPickerField(
    selectedColor: String,
    onColorSelected: (String) -> Unit
) {

    var showPicker by remember {
        mutableStateOf(false)
    }

    val displayColor = hexToColor(selectedColor)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Color",
            fontSize = 14.sp
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(displayColor)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = CircleShape
                    )
            )

            Button(
                onClick = {
                    showPicker = true
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.DarkGray
                )
            ) {

                Text(
                    text = if (
                        selectedColor.isBlank()
                    ) {
                        "CHOOSE COLOR"
                    } else {
                        selectedColor
                    }
                )
            }
        }
    }

    if (showPicker) {

        ColorPickerDialog(
            initialColor = displayColor,
            onDismiss = {
                showPicker = false
            },
            onColorSelected = { color ->

                onColorSelected(
                    colorToHex(color)
                )

                showPicker = false
            }
        )
    }
}


@Composable
private fun ColorPickerDialog(
    initialColor: Color,
    onDismiss: () -> Unit,
    onColorSelected: (Color) -> Unit
) {

    val initialHsv = remember(initialColor) {
        colorToHsv(initialColor)
    }

    var hue by remember {
        mutableStateOf(initialHsv[0])
    }

    var saturation by remember {
        mutableStateOf(initialHsv[1])
    }

    var brightness by remember {
        mutableStateOf(initialHsv[2])
    }

    val selectedColor = Color.hsv(
        hue,
        saturation,
        brightness
    )

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {
            Text("Choose Color")
        },

        text = {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // -------------------------------------------------
                // COLOR WHEEL
                // -------------------------------------------------

                HueColorWheel(
                    hue = hue,
                    onHueChanged = {
                        hue = it
                    }
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                // -------------------------------------------------
                // SATURATION / BRIGHTNESS AREA
                // -------------------------------------------------

                SaturationBrightnessPicker(
                    hue = hue,
                    saturation = saturation,
                    brightness = brightness,
                    onColorChanged = { newSaturation, newBrightness ->

                        saturation = newSaturation
                        brightness = newBrightness
                    }
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                // -------------------------------------------------
                // PREVIEW
                // -------------------------------------------------

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(selectedColor)
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = CircleShape
                            )
                    )

                    Text(
                        text = colorToHex(selectedColor)
                    )
                }
            }
        },

        confirmButton = {

            Button(
                onClick = {
                    onColorSelected(selectedColor)
                }
            ) {
                Text("SELECT")
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {
                Text("CANCEL")
            }
        }
    )
}


@Composable
private fun HueColorWheel(
    hue: Float,
    onHueChanged: (Float) -> Unit
) {

    Canvas(
        modifier = Modifier
            .size(220.dp)
            .pointerInput(Unit) {

                detectTapGestures { offset ->

                    val center = Offset(
                        size.width / 2f,
                        size.height / 2f
                    )

                    val dx = offset.x - center.x
                    val dy = offset.y - center.y

                    var angle =
                        Math.toDegrees(
                            atan2(
                                dy.toDouble(),
                                dx.toDouble()
                            )
                        ).toFloat()

                    angle += 90f

                    if (angle < 0f) {
                        angle += 360f
                    }

                    onHueChanged(
                        angle % 360f
                    )
                }
            }
    ) {

        val center = Offset(
            size.width / 2f,
            size.height / 2f
        )

        val radius =
            min(size.width, size.height) / 2f

        val colors = (0..360).map { degree ->

            Color.hsv(
                degree.toFloat(),
                1f,
                1f
            )
        }

        drawArc(
            brush = Brush.sweepGradient(colors),
            startAngle = -90f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(
                width = radius * 0.25f
            )
        )

        // Selection indicator

        val angle =
            Math.toRadians(
                (hue - 90f).toDouble()
            )

        val indicatorRadius =
            radius * 0.875f

        val indicatorCenter = Offset(
            x = center.x +
                    cos(angle).toFloat() *
                    indicatorRadius,

            y = center.y +
                    sin(angle).toFloat() *
                    indicatorRadius
        )

        drawCircle(
            color = Color.White,
            radius = radius * 0.07f,
            center = indicatorCenter
        )

        drawCircle(
            color = Color.Black,
            radius = radius * 0.07f,
            center = indicatorCenter,
            style = Stroke(
                width = 2f
            )
        )
    }
}


@Composable
private fun SaturationBrightnessPicker(
    hue: Float,
    saturation: Float,
    brightness: Float,
    onColorChanged: (Float, Float) -> Unit
) {

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .pointerInput(hue) {

                detectTapGestures { offset ->

                    val newSaturation =
                        (
                                offset.x /
                                        size.width
                                )
                            .coerceIn(0f, 1f)

                    val newBrightness =
                        (
                                1f -
                                        offset.y /
                                        size.height
                                )
                            .coerceIn(0f, 1f)

                    onColorChanged(
                        newSaturation,
                        newBrightness
                    )
                }
            }
    ) {

        // Horizontal saturation gradient
        val saturationGradient =
            Brush.horizontalGradient(
                colors = listOf(
                    Color.White,
                    Color.hsv(
                        hue,
                        1f,
                        1f
                    )
                )
            )

        drawRect(
            brush = saturationGradient
        )

        // Vertical brightness gradient
        val brightnessGradient =
            Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent,
                    Color.Black
                )
            )

        drawRect(
            brush = brightnessGradient
        )

        // Selection indicator
        val x =
            saturation * size.width

        val y =
            (1f - brightness) *
                    size.height

        drawCircle(
            color = Color.White,
            radius = 9f,
            center = Offset(x, y)
        )

        drawCircle(
            color = Color.Black,
            radius = 9f,
            center = Offset(x, y),
            style = Stroke(
                width = 2f
            )
        )
    }
}


private fun colorToHex(
    color: Color
): String {

    val red =
        (color.red * 255)
            .roundToInt()
            .coerceIn(0, 255)

    val green =
        (color.green * 255)
            .roundToInt()
            .coerceIn(0, 255)

    val blue =
        (color.blue * 255)
            .roundToInt()
            .coerceIn(0, 255)

    return String.format(
        "#%02X%02X%02X",
        red,
        green,
        blue
    )
}


private fun hexToColor(
    hex: String
): Color {

    return try {

        Color(
            android.graphics.Color.parseColor(
                hex
            )
        )

    } catch (e: Exception) {

        Color.LightGray
    }
}


private fun colorToHsv(
    color: Color
): FloatArray {

    val hsv = FloatArray(3)

    android.graphics.Color.RGBToHSV(
        (color.red * 255).roundToInt(),
        (color.green * 255).roundToInt(),
        (color.blue * 255).roundToInt(),
        hsv
    )

    return hsv
}