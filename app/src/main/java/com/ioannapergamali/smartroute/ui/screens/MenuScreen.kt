package com.ioannapergamali.smartroute.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ioannapergamali.smartroute.model.Role
import com.ioannapergamali.smartroute.model.User

@Composable
fun MenuScreen(
        user : User? ,
        userRole : Role ,
        navController : NavController ,
        onNavigateToSettings : () -> Unit
)
{
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
                text = "Welcome to the Menu" ,
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge
        )

        // Εμφάνιση του ρόλου χρήστη
        Text(
                text = "Role: ${userRole.name}" ,
                modifier = Modifier.padding(vertical = 8.dp) ,
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
        )

        // Εμφάνιση στοιχείων χρήστη αν υπάρχουν
        user?.let {
            Text(
                    text = "User Details:" ,
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
            )
            Text(text = "Name: ${it.getName()} ${it.getSurname()}")
            Text(text = "Email: ${it.getEmail()}")
        } ?: run {
            Text(
                    text = "No user information available" ,
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall
            )
        }

        // Κουμπί πλοήγησης στις ρυθμίσεις
        Button(onClick = onNavigateToSettings) {
            Text(text = "Go to Settings")
        }
    }
}

