package com.ioannapergamali.smartroute.ui.screens

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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ioannapergamali.smartroute.ui.components.DrawerScaffold

@Composable
fun LoginScreen(
        navController : NavController ,
        onLoginSuccess : () -> Unit ,
        onLoginFailure : (String) -> Unit ,
        onNavigateToSettings : () -> Unit
)
{
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val loginError = remember { mutableStateOf("") }

    DrawerScaffold(
            title = "Login" ,
            onSettingsClick = onNavigateToSettings ,
            onLogoutClick = { navController.navigate("splash") }
    ) { paddingValues ->
        Box(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues) ,
                contentAlignment = Alignment.Center
        ) {
            Column(
                    modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(16.dp)
            ) {
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
                        if (email.value == "user@example.com" && password.value == "password")
                        {
                            // Mock login logic
                            onLoginSuccess()
                        }
                        else
                        {
                            loginError.value = "Invalid email or password"
                            onLoginFailure(loginError.value)
                        }
                    }
                    else
                    {
                        loginError.value = "Please fill in all fields"
                    }
                }) {
                    Text("Login")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    navController.navigate("signup")
                }) {
                    Text("Create an Account")
                }
            }
        }
    }
}

