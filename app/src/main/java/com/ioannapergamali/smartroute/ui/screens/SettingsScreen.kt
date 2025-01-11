package com.ioannapergamali.smartroute.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ioannapergamali.smartroute.ui.components.DrawerScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
        navController : NavController ,
        onThemeChange : (Boolean) -> Unit
)
{
    DrawerScaffold(
            title = "Settings" ,
            onSettingsClick = { navController.navigate("settings") } ,
            onLogoutClick = { navController.navigate("login") }
    ) { paddingValues ->
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
        ) {
            Text(text = "Theme Settings" , style = MaterialTheme.typography.headlineMedium)
            Switch(
                    checked = true , // Δείγμα λογικής
                    onCheckedChange = { onThemeChange(it) }
            )
        }
    }
}
