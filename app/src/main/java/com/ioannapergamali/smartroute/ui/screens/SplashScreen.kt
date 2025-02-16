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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ioannapergamali.smartroute.R
import com.ioannapergamali.smartroute.ui.components.TopBar

@Composable
fun SplashScreen(
    navController: NavController,
    onNavigateToLogin: () -> Unit,
    onNavigateToSignUp: () -> Unit // Σωστό όνομα!
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Splash Screen",
                navController = navController
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to MoveWise",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                GlideComposeScreen(
                    imageResId = R.drawable.mw,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = { onNavigateToLogin() }) {
                        Text("Login")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { onNavigateToSignUp() }) { // Εδώ αλλάζουμε το όνομα
                        Text("Sign Up")
                    }
                }
            }
        }
    )
}
