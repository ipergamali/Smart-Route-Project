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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ioannapergamali.smartroute.viewmodel.AuthenticationViewModel

@Composable
fun SignUpScreen(
        navController : NavController ,
        onSignUpSuccess : () -> Unit ,
        onSignUpFailure : (String) -> Unit
)
{
    val viewModel: AuthenticationViewModel = viewModel()
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val surname = remember { mutableStateOf("") }
    val signupError = remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(16.dp)
        ) {
            TextField(
                    value = name.value ,
                    onValueChange = { name.value = it } ,
                label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                    value = surname.value ,
                    onValueChange = { surname.value = it } ,
                label = { Text("Surname") },
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
            if (signupError.value.isNotEmpty()) {
                Text(
                    text = signupError.value,
                    color = MaterialTheme.colorScheme.error
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (email.value.isNotEmpty() && password.value.isNotEmpty())
                {
                    // Logic for sign-up
                    onSignUpSuccess()
                    signupError.value = ""
                }
                else
                {
                    signupError.value = "All fields are required"
                    onSignUpFailure(signupError.value)
                }
            }) {
                Text("Sign Up")
            }
        }
    }
}
