package com.ioannapergamali.smartroute.ui.screens

import androidx.compose.foundation.layout.Arrangement
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
import com.ioannapergamali.smartroute.R

import com.ioannapergamali.smartroute.ui.components.DrawerScaffold

@Composable
fun SplashScreen(
        navController : NavController , // Προσθήκη του navController
        onNavigateToLogin : () -> Unit ,
        onNavigateToSignUp : () -> Unit
)
{
    DrawerScaffold(
            title = "Welcome" ,
            onSettingsClick = { navController.navigate("settings") } ,
            onLogoutClick = { navController.navigate("login") }
    ) { paddingValues ->
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp) ,
                verticalArrangement = Arrangement.Top ,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Κείμενο καλωσορίσματος
            Text(
                    text = "Welcome to " ,
                    style = MaterialTheme.typography.headlineLarge ,
                    modifier = Modifier.padding(vertical = 8.dp)
            )

            // Προσθήκη Λογότυπου με Glide
            GlideComposeScreen(
                    imageResId = R.drawable.sr ,
                    modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp) // Περιορισμός ύψους του GIF
            )

            // Κουμπιά Login και Sign Up
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { onNavigateToLogin() }) {
                    Text("Login")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { onNavigateToSignUp() }) {
                    Text("Sign Up")
                }
            }
        }
    }
}
