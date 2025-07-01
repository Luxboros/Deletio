package com.luxboros.deletio.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


data class CustomColors(
    val positiveSurface: Color,
    val positiveLabel: Color,
    val positiveContent: Color,
    val infoSurface: Color,
    val infoLabel: Color,
    val infoContent: Color,
    val warningSurface: Color,
    val warningLabel: Color,
    val warningContent: Color,
    val textSecondary: Color,
)

val LightCustomColors = CustomColors(
    positiveSurface = LightPositiveSurface,
    positiveLabel = LightPositiveLabel,
    positiveContent = LightPositiveContent,
    infoSurface = LightInfoSurface,
    infoLabel = LightInfoLabel,
    infoContent = LightInfoContent,
    warningSurface = LightWarningSurface,
    warningLabel = LightWarningLabel,
    warningContent = LightWarningContent,
    textSecondary = LightTextSecondary
)

val DarkCustomColors = CustomColors(
    positiveSurface = DarkPositiveSurface,
    positiveLabel = DarkPositiveLabel,
    positiveContent = DarkPositiveContent,
    infoSurface = DarkInfoSurface,
    infoLabel = DarkInfoLabel,
    infoContent = DarkInfoContent,
    warningSurface = DarkWarningSurface,
    warningLabel = DarkWarningLabel,
    warningContent = DarkWarningContent,
    textSecondary = DarkTextSecondary
)

val LocalDeletioColors = staticCompositionLocalOf { LightCustomColors }

// Standard Material Palettes (using colors from Color.kt)
private val LightColorPalette = lightColorScheme(
    primary = LightAccent,
    background = LightBackground,
    surface = LightSurface,
    onPrimary = LightOnAccent,
    onBackground = LightTextPrimary,
    onSurface = LightTextPrimary
)
private val DarkColorPalette = darkColorScheme(
    primary = DarkAccent,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = DarkOnAccent,
    onBackground = DarkTextPrimary,
    onSurface = DarkTextPrimary,
)


@Composable
fun CustomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    val customColors = if (darkTheme) DarkCustomColors else LightCustomColors

    CompositionLocalProvider(LocalDeletioColors provides customColors) {
        MaterialTheme(
            colorScheme = colors,
            typography = MaterialTheme.typography,
            shapes = MaterialTheme.shapes,
            content = content
        )
    }
}