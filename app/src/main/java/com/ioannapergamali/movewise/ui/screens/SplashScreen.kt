package com.ioannapergamali.movewise.ui.screens

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
import com.ioannapergamali.movewise.R
import com.ioannapergamali.movewise.ui.components.TopBar

@Composable
fun SplashScreen(
        navController : NavController , // Προσθήκη του navController
        onNavigateToLogin : () -> Unit ,
        onNavigateToSettings : () -> Unit
)
{
    Scaffold(
            topBar = {
                TopBar(
                        title = "Splash Screen" ,
                        navController = navController
                )
            } , // Περνάμε το navController
            content = { paddingValues ->
                Column(
                        modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                                .padding(16.dp) ,
                        verticalArrangement = Arrangement.SpaceBetween ,
                        horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Κείμενο καλωσορίσματος
                    Text(
                            text = "Welcome to MoveWise" ,
                            style = MaterialTheme.typography.headlineLarge ,
                            modifier = Modifier.padding(vertical = 8.dp)
                    )

                    // Προσθήκη GIF
                    GlideComposeScreen(
                            imageResId = R.drawable.mw ,
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp) // Περιορισμός ύψους του GIF
                    )

                    // Κουμπιά
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = { onNavigateToLogin() }) {
                            Text("Login")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { onNavigateToSettings() }) {
                            Text("Sign Up")
                        }
                    }
                }
            }
    )
}
