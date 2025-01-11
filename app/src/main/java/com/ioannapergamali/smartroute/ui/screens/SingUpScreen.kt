package com.ioannapergamali.smartroute.ui.screens

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
import androidx.navigation.NavController
import com.ioannapergamali.smartroute.ui.components.AnimatedScaleImage

@Composable
fun SignUpScreen(
        navController : NavController ,
        onSignUpSuccess : () -> Unit ,
        onSignUpFailure : (String) -> Unit
)
{
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val signUpError = remember { mutableStateOf("") }

    Scaffold(
            topBar = {
                TopBar(
                        title = "Sign Up" ,
                        navController = navController
                )
            }
    ) { innerPadding ->
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(32.dp) ,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Προσθήκη Animation Εικόνας
            AnimatedScaleImage()

            Spacer(modifier = Modifier.height(64.dp))

            // Fields and Buttons
            TextField(
                    value = name.value ,
                    onValueChange = { name.value = it } ,
                    label = { Text("Name") } ,
                    modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
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
            if (signUpError.value.isNotEmpty())
            {
                Text(
                        text = signUpError.value ,
                        color = MaterialTheme.colorScheme.error
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (name.value.isNotEmpty() && email.value.isNotEmpty() && password.value.isNotEmpty())
                {
                    onSignUpSuccess()
                }
                else
                {
                    signUpError.value = "Please fill in all fields"
                }
            }) {
                Text("Sign Up")
            }
        }
    }
}

