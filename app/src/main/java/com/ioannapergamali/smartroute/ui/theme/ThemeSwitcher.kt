package com.ioannapergamali.smartroute.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ioannapergamali.smartroute.viewmodel.SettingsViewModel

@Composable
fun ThemeSwitcher(settingsViewModel : SettingsViewModel = viewModel())
{
    val isDarkTheme = settingsViewModel.isDarkTheme.collectAsState(initial = false)

    Column(
            modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Change Theme" , style = MaterialTheme.typography.headlineLarge)

        Switch(
                checked = isDarkTheme.value ,
                onCheckedChange = { settingsViewModel.toggleTheme(it) }
        )
        Text(
                text = if (isDarkTheme.value) "Dark Theme Enabled" else "Light Theme Enabled" ,
                style = MaterialTheme.typography.bodyLarge
        )
    }
}
