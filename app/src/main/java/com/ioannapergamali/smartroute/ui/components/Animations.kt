package com.ioannapergamali.smartroute.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ioannapergamali.smartroute.R

@Composable
fun AnimatedScaleImage()
{
    val scale = remember { Animatable(1f) }

    // Infinite animation for scaling
    LaunchedEffect(Unit) {
        scale.animateTo(
                targetValue = 3f ,
                animationSpec = infiniteRepeatable(
                        animation = tween(1000 , easing = LinearEasing) ,
                        repeatMode = RepeatMode.Reverse
                )
        )
    }

    Image(
            painter = painterResource(id = R.drawable.sr) , // Replace with your drawable resource
            contentDescription = null ,
            modifier = Modifier
                    .scale(scale.value)
    )
}


@Composable
fun AnimatedText(text : String)
{
    val alpha = remember { Animatable(0f) } // Ξεκινά με 0 (διαφανές)
    val scale = remember { Animatable(0.8f) } // Ξεκινά με μέγεθος 0.8

    // Animation Effect
    LaunchedEffect(Unit) {
        alpha.animateTo(
                targetValue = 1f , // Γίνεται πλήρως ορατό
                animationSpec = tween(2000) // Διαρκεί 1000ms
        )
        scale.animateTo(
                targetValue = 1f , // Κανονικό μέγεθος
                animationSpec = tween(5000) // Διαρκεί 1000ms
        )
    }
    val BigTangleFont = FontFamily(
            Font(
                    R.font.bigtangle ,
                    FontWeight.Normal
            ) // Αντικατάστησε το όνομα αν είναι διαφορετικό
    )
    // Εμφάνιση του κειμένου με animations
    Text(
            text = text ,
            style = TextStyle(
                    fontFamily = BigTangleFont ,
                    fontSize = 40.sp ,
                    color = Color.Green
            ) ,
            modifier = Modifier
                    .scale(scale.value) // Εφαρμογή του scale animation
                    .alpha(alpha.value) // Εφαρμογή του alpha animation
    )
}
