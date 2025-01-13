package com.ioannapergamali.smartroute.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ioannapergamali.smartroute.data.createUser
import com.ioannapergamali.smartroute.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
        navController : NavController ,
        onSignUpSuccess : () -> Unit ,
        onSignUpFailure : (String) -> Unit
)
{
    val name = remember { mutableStateOf("") }
    val surname = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phoneNum = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val street = remember { mutableStateOf("") }
    val number = remember { mutableStateOf("") }
    val postalCode = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val selectedRole = remember { mutableStateOf("") }
    val signUpError = remember { mutableStateOf("") }

    val roles = listOf("Passenger" , "Driver")
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val expanded = remember { mutableStateOf(false) }

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
                        .padding(32.dp)
                        .verticalScroll(rememberScrollState()) ,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Fields for User Data
            TextField(
                    value = name.value ,
                    onValueChange = { name.value = it } ,
                    label = { Text("First Name") } ,
                    modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                    value = surname.value ,
                    onValueChange = { surname.value = it } ,
                    label = { Text("Last Name") } ,
                    modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                    value = username.value ,
                    onValueChange = { username.value = it } ,
                    label = { Text("Username") } ,
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
                    value = phoneNum.value ,
                    onValueChange = { phoneNum.value = it } ,
                    label = { Text("Phone Number") } ,
                    modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                    value = city.value ,
                    onValueChange = { city.value = it } ,
                    label = { Text("City") } ,
                    modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                    value = street.value ,
                    onValueChange = { street.value = it } ,
                    label = { Text("Street") } ,
                    modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                    value = number.value ,
                    onValueChange = { number.value = it } ,
                    label = { Text("House Number") } ,
                    modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                    value = postalCode.value ,
                    onValueChange = { postalCode.value = it } ,
                    label = { Text("Postal Code") } ,
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

            // Role Selection Dropdown
            ExposedDropdownMenuBox(
                    expanded = expanded.value ,
                    onExpandedChange = { expanded.value = !expanded.value }
            ) {
                TextField(
                        value = selectedRole.value ,
                        onValueChange = {} ,
                        readOnly = true ,
                        label = { Text("Select Role") } ,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
                        } ,
                        modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(type , enabled)
                )
                ExposedDropdownMenu(
                        expanded = expanded.value ,
                        onDismissRequest = { expanded.value = false }
                ) {
                    roles.forEach { role ->
                        DropdownMenuItem(
                                text = { Text(role) } ,
                                onClick = {
                                    selectedRole.value = role
                                    expanded.value = false
                                }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (email.value.isNotEmpty() && password.value.isNotEmpty() && selectedRole.value.isNotEmpty())
                {
                    createUser(
                            auth = auth ,
                            firestore = firestore ,
                            email = email.value ,
                            password = password.value ,
                            name = name.value ,
                            surname = surname.value ,
                            username = username.value ,
                            phoneNum = phoneNum.value ,
                            city = city.value ,
                            street = street.value ,
                            number = number.value ,
                            postalCode = postalCode.value ,
                            role = selectedRole.value ,
                            onSuccess = {
                                onSignUpSuccess()
                            } ,
                            onFailure = { error ->
                                signUpError.value = error
                            }
                    )
                }
                else
                {
                    signUpError.value = "Please fill in all fields."
                }
            }) {
                Text("Sign Up")
            }

            if (signUpError.value.isNotEmpty())
            {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                        text = signUpError.value ,
                        color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

