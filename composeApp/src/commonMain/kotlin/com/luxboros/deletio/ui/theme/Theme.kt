package com.luxboros.deletio.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color


//Light Color Palette v2
val LightThemeColors = lightColorScheme(
    primary = PurpleAccent,
    onPrimary = DarkText,
    primaryContainer = Color(0xFFE9D5F2),
    onPrimaryContainer = DarkText,
    secondary = PurpleAccentText,
    background = OffWhite,
    onBackground = DarkText,
    surface = White,
    onSurface = DarkText,
    onSurfaceVariant = LightGrey,
    outline = BorderGrey,
    error = ErrorRed,
    errorContainer = ErrorRedBg,
    onErrorContainer = ErrorRed
)

//Dark Color Palette v2
val DarkThemeColors = darkColorScheme(
    primary = PurpleAccent,
    onPrimary = DarkText,
    primaryContainer = Color(0xFF4A3A59),
    onPrimaryContainer = OffWhiteText,
    secondary = PurpleAccentTextDark,
    background = DarkIndigoBg,
    onBackground = OffWhiteText,
    surface = DarkIndigoSurface,
    onSurface = OffWhiteText,
    onSurfaceVariant = GreyText,
    outline = DarkBorder,
    error = ErrorRedDark,
    errorContainer = ErrorRedBgDark,
    onErrorContainer = ErrorRedDark
)


@Composable
fun CustomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkThemeColors else LightThemeColors
    val typography = DeletioTypography()

    CompositionLocalProvider {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = MaterialTheme.shapes,
            content = content
        )
    }
}