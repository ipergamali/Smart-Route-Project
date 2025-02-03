package com.ioannapergamali.smartroute.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
        primary = GreenPrimary ,
        secondary = SecondaryColor ,
        background = BackgroundColor ,
        surface = BackgroundColor ,
        onPrimary = TextColor ,
        onSecondary = TextColor ,
        onBackground = TextColor ,
        onSurface = TextColor
)

private val DarkColors = darkColorScheme(
        primary = GreenDark ,
        secondary = DarkSecondaryColor ,
        background = DarkBackground ,
        surface = DarkBackground ,
        onPrimary = DarkTextColor ,
        onSecondary = DarkTextColor ,
        onBackground = DarkTextColor ,
        onSurface = DarkTextColor
)

@Composable
fun SmartRouteTheme(
        darkTheme : Boolean = isSystemInDarkTheme() ,
        content : @Composable () -> Unit
)
{
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
            colorScheme = colors ,
            typography = Typography ,
            shapes = Shapes ,
            content = content
    )
}
