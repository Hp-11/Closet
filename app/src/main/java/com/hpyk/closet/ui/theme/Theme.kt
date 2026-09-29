package com.hpyk.closet.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = BoutiqueBlush,
    secondary = BurgundyLight,
    background = BurgundyDark,
    surface = BurgundyDark,
    onPrimary = BurgundyDark,
    onBackground = Ivory,
    onSurface = Ivory,
)

private val LightColorScheme = lightColorScheme(
    primary = Burgundy,
    secondary = BurgundyLight,
    tertiary = BoutiqueBlush,
    background = SoftSurface,
    surface = Ivory,
    onPrimary = Ivory,
    onSecondary = Ivory,
    onBackground = Charcoal,
    onSurface = Charcoal,
)

@Composable
fun ClosetTheme(content: @Composable () -> Unit) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
