package com.ioannapergamali.smartroute.ui.screens

import AuthenticationViewModelFactory
import android.app.Application
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
import com.ioannapergamali.smartroute.ui.components.TopBar
import com.ioannapergamali.smartroute.utils.UserSession
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
                        showBackButton = true
                )
            }
    ) { innerPadding ->
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp) ,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Προσθήκη Animation Εικόνας
            AnimatedScaleImage()

            Spacer(modifier = Modifier.height(64.dp))

            // Fields and Buttons
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
            if (loginError.value.isNotEmpty())
            {
                Text(
                        text = loginError.value ,
                        color = MaterialTheme.colorScheme.error
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (email.value.isNotEmpty() && password.value.isNotEmpty())
                {
                    authenticationViewModel.loginUser(
                            email.value ,
                            password.value ,
                            onLoginSuccess = {
                                authenticationViewModel.getUserData(email.value) { user ->
                                    if (user != null)
                                    {

                                        // Αποθήκευση του χρήστη στη συνεδρία
                                        UserSession.setUser(
                                                context = navController.context ,
                                                user = user
                                        )

                                        // Επιτυχής σύνδεση
                                        onLoginSuccess()
                                    }
                                    else
                                    {
                                        loginError.value = "User not found or error occurred."
                                        onLoginFailure("User not found or error occurred.")
                                    }
                                }
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
            Button(onClick = onNavigateToSettings) {
                Text("Go to Settings")
            }
        }
    }
}
