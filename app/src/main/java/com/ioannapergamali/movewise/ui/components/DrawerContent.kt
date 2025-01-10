package com.ioannapergamali.movewise.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(
        onSettingsClick : () -> Unit ,
        onLogoutClick : () -> Unit
)
{
    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
    ) {
        Text(
                text = "Menu" ,
                style = MaterialTheme.typography.headlineMedium ,
                modifier = Modifier.padding(bottom = 24.dp)
        )
        HorizontalDivider()

        Spacer(modifier = Modifier.height(16.dp))

        // Ρυθμίσεις
        TextButton(onClick = onSettingsClick) {
            Text("Settings")
        }

        // Αποσύνδεση
        TextButton(onClick = onLogoutClick) {
            Text("Logout")
        }
    }
}
