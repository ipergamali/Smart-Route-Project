package com.ioannapergamali.movewise.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ioannapergamali.movewise.model.User

@Composable
fun MenuScreen(
        user : User? ,
        userRole : String ,
        navController : NavController ,
        onNavigateToSettings : () -> Unit
)
{
    Box(
            modifier = Modifier.fillMaxSize() ,
            contentAlignment = Alignment.Center
    ) {
        Column(
                modifier = Modifier.fillMaxWidth(0.8f) ,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                    text = "Welcome to the Menu" ,
                    style = MaterialTheme.typography.headlineLarge ,
                    modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(text = "Role: $userRole")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateToSettings) {
                Text("Settings")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("login") }) {
                Text("Log Out")
            }
        }
    }
}

