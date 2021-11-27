package mx.nutritivalabs.nutritivapp.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/*
private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)
*/

private val LightColorPalette = lightColors(
    primary = green500,
    primaryVariant = green700,
    secondary = blue500,
    secondaryVariant = blue700,
    background = whiteSmoke,
    surface = white,
    onPrimary = white,
    onSecondary = white,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun NutritivappTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}