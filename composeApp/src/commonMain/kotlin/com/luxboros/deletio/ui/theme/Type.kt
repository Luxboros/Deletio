package com.luxboros.deletio.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import deletio.composeapp.generated.resources.Inter_Variable
import deletio.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun InterFontFamily() = FontFamily(
    Font(Res.font.Inter_Variable, FontWeight.Normal),
    Font(Res.font.Inter_Variable, FontWeight.Bold),
    Font(Res.font.Inter_Variable, FontWeight.Medium),
    Font(Res.font.Inter_Variable, FontWeight.SemiBold),
    Font(Res.font.Inter_Variable, FontWeight.Black),
    Font(Res.font.Inter_Variable, FontWeight.Thin),
    Font(Res.font.Inter_Variable, FontWeight.ExtraBold),
    Font(Res.font.Inter_Variable, FontWeight.ExtraLight),
    Font(Res.font.Inter_Variable, FontWeight.Light)
)

@Composable
fun DeletioTypography(): Typography {
    val inter = InterFontFamily()
    return Typography(
        headlineSmall = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 32.sp
        ), titleMedium = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp
        ), bodyLarge = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 28.sp
        ), labelMedium = TextStyle(
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp
        )
    )
}