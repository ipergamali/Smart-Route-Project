package com.ioannapergamali.smartroute.ui.screens

import AuthenticationViewModelFactory
import android.app.Application
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ioannapergamali.smartroute.ui.components.AnimatedScaleImage
import com.ioannapergamali.smartroute.viewmodel.AuthenticationViewModel


@Composable
fun LoginScreen(
        navController : NavController ,
        authenticationViewModel : AuthenticationViewModel = viewModel(
                factory = AuthenticationViewModelFactory(navController.context.applicationContext as Application)
        ) ,
        onLoginSuccess : () -> Unit ,
        onLoginFailure : (String) -> Unit ,
        onNavigateToSettings : () -> Unit
)
{
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val loginError = remember { mutableStateOf("") }

    Scaffold(
            topBar = {
                TopBar(
                        title = "Login" ,
                        navController = navController ,
                        showBackButton = false
                )
            }
    ) { innerPadding ->
        Box(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding) ,
                contentAlignment = Alignment.Center
        ) {
            Column(
                    modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(16.dp) ,
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Προσθήκη Animation Εικόνας
                AnimatedScaleImage()

                Spacer(modifier = Modifier.height(32.dp))

                // Text Fields for Email and Password
                TextField(
                        value = email.value ,
                        onValueChange = { email.value = it } ,
                        label = { Text("Email") } ,
                        modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                        value = password.value ,
                        onValueChange = { password.value = it } ,
                        label = { Text("Password") } ,
                        modifier = Modifier.fillMaxWidth() ,
                        visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Error Message
                if (loginError.value.isNotEmpty())
                {
                    Text(
                            text = loginError.value ,
                            color = MaterialTheme.colorScheme.error
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Login Button
                Button(onClick = {
                    if (email.value.isNotEmpty() && password.value.isNotEmpty())
                    {
                        authenticationViewModel.loginUser(
                                email.value ,
                                password.value ,
                                onLoginSuccess = {
                                    onLoginSuccess()
                                } ,
                                onLoginFailure = { error ->
                                    loginError.value = error
                                    onLoginFailure(error)
                                }
                        )
                    }
                    else
                    {
                        loginError.value = "Please fill in all fields"
                    }
                }) {
                    Text("Login")
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Navigate to Settings
                Button(onClick = onNavigateToSettings) {
                    Text("Go to Settings")
                }
            }
        }
    }
}
