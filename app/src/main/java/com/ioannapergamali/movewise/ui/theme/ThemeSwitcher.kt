package com.ioannapergamali.movewise.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ThemeSwitcher()
{
    val darkTheme = remember { mutableStateOf(false) }

    Scaffold(
            content = { paddingValues : PaddingValues ->
                Column(
                        modifier = Modifier
                                .padding(paddingValues)
                                .padding(16.dp)
                ) {
                    Text(text = "Change Theme" , style = MaterialTheme.typography.headlineLarge)

                    // Switch για αλλαγή θέματος
                    Switch(
                            checked = darkTheme.value ,
                            onCheckedChange = { darkTheme.value = it }
                    )
                    // Χρήση του MoveWiseTheme με την επιλογή θέματος
                    MoveWiseTheme(darkTheme = darkTheme.value) {
                        Text(
                                text = if (darkTheme.value) "Dark Theme Enabled" else "Light Theme Enabled" ,
                                style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
    )
}

@Preview
@Composable
fun ThemePreview()
{
    MoveWiseTheme {
        ThemeSwitcher()
    }
}
