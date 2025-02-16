package com.ioannapergamali.smartroute.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ioannapergamali.smartroute.ui.components.DrawerScaffold

@Composable
fun HomeScreen(
        navController : NavController ,
        userRole : String
)
{
    DrawerScaffold(
            title = "MoveWise Home" ,
            onSettingsClick = { navController.navigate("settings") } ,
            onLogoutClick = { navController.navigate("login") }
    ) { paddingValues ->
        Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
        ) {
            Text(
                    text = "Welcome, $userRole" ,
                    style = MaterialTheme.typography.headlineLarge ,
                    modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
