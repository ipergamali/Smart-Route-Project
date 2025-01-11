package com.ioannapergamali.smartroute.ui.screens

import android.widget.ImageView
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide

@Composable
fun GlideComposeScreen(imageResId : Int , modifier : Modifier = Modifier)
{
    Box(
            modifier = modifier ,
            contentAlignment = Alignment.Center
    ) {
        AndroidView(
                factory = { context ->
                    ImageView(context).apply {
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }
                } ,
                update = { imageView ->
                    Glide.with(imageView.context)
                            .load(imageResId)
                            .into(imageView)
                }
        )
    }
}
