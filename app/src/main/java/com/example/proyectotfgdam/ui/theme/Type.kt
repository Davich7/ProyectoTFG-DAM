package com.example.proyectotfg_dam.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp
import com.example.proyectotfgdam.R

// Declaramos la familia de fuentes Roboto
val robotoFontFamily = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_italic, FontWeight.Normal, androidx.compose.ui.text.font.FontStyle.Italic),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_light, FontWeight.Light)
)

// Declaramos la familia de fuentes de Roboto Serif (en este caso son fuentes variables)

val robotoSerifRegularFontFamily = FontFamily(
    Font(R.font.roboto_serif_variable) // Usar para texto regular
)

val robotoSerifItalicFontFamily = FontFamily(
    Font(R.font.roboto_serif_italic_variable) // Usar para texto en cursiva
)

// Default Material 3 typography values
val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = robotoSerifRegularFontFamily, // Usar Roboto Serif Regular
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp
    ),
    displayMedium = TextStyle(
        fontFamily = robotoSerifRegularFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp
    ),
    displaySmall = TextStyle(
        fontFamily = robotoSerifRegularFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = robotoSerifRegularFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = robotoSerifRegularFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = robotoSerifRegularFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = robotoSerifRegularFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = robotoFontFamily, // Usar Roboto Regular
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = robotoFontFamily, // Usar Roboto Regular
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = robotoFontFamily, // Usar Roboto Regular
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = robotoFontFamily, // Usar Roboto Regular
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = robotoFontFamily, // Usar Roboto Regular
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = robotoFontFamily, // Usar Roboto Regular
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = robotoFontFamily, // Usar Roboto Regular
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = robotoFontFamily, // Usar Roboto Regular
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
)