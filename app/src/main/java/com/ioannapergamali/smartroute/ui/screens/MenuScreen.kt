package com.ioannapergamali.smartroute.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ioannapergamali.smartroute.model.Menu
import com.ioannapergamali.smartroute.model.MenuAction
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
    // Δημιουργία μενού βάσει ρόλου
    val menu = Menu(userRole)

    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp) ,
            verticalArrangement = Arrangement.Top ,
            horizontalAlignment = Alignment.Start
    ) {
        // Καλωσόρισμα
        Text(
                text = "Welcome to the Menu" ,
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Εμφάνιση του ρόλου χρήστη
        Text(
                text = "Role: ${userRole.name}" ,
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Εμφάνιση στοιχείων χρήστη
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

        Spacer(modifier = Modifier.height(24.dp))

        // Ενέργειες βάσει μενού
        Text(
                text = "Available Actions:" ,
                style = androidx.compose.material3.MaterialTheme.typography.titleMedium
        )

        menu.actions.forEach { action ->
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                handleMenuAction(action , navController , onNavigateToSettings)
            }) {
                Text(text = action.description)
            }
        }
    }
}

private fun handleMenuAction(
        menuAction : MenuAction ,
        navController : NavController ,
        onNavigateToSettings : () -> Unit
)
{
    when (menuAction.description)
    {
        "Sign out"                           -> navController.navigate("login")
        "Manage Favorite Means of Transport" -> navController.navigate("favorite_transport")
        "Register Vehicle"                   -> navController.navigate("register_vehicle")
        "Initialize System"                  -> navController.navigate("initialize_system")
        "Go to Settings"                     -> onNavigateToSettings()
        else                                 -> menuAction.action.invoke() // Εκτέλεση custom δράσης
    }
}
