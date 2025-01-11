package com.ioannapergamali.smartroute.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(
        modifier : Modifier = Modifier ,
        onSettingsClick : () -> Unit ,
        onLogoutClick : () -> Unit
)
{
    Box(
            modifier = modifier
                    .fillMaxSize()
                    .border(2.dp , Color.Gray)
    ) {
        Text("Menu Item 1") // Placeholder for settings
        Text("Menu Item 2") // Placeholder for logout
    }
}
