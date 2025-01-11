package com.ioannapergamali.smartroute.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ioannapergamali.smartroute.model.Menu
import com.ioannapergamali.smartroute.model.MenuAction
import com.ioannapergamali.smartroute.model.Role
import com.ioannapergamali.smartroute.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
        user : User? ,
        userRole : Role ,
        navController : NavController ,
        onNavigateToSettings : () -> Unit
)
{
    val menu = Menu(userRole)

    Scaffold(
            topBar = {
                TopAppBar(
                        title = { Text(text = "SmartRoute Menu") } ,
                        actions = {
                            IconButton(onClick = { onNavigateToSettings() }) {
                                Icon(
                                        imageVector = Icons.Default.Settings ,
                                        contentDescription = "Settings"
                                )
                            }
                        } ,
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary ,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                        )
                )
            }
    ) { innerPadding ->
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp) ,
                verticalArrangement = Arrangement.Top ,
                horizontalAlignment = Alignment.Start
        ) {
            // Welcome Message
            Text(
                    text = "Welcome to SmartRoute!" ,
                    style = MaterialTheme.typography.headlineMedium ,
                    color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // User Information
            user?.let {
                Card(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp) ,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                                text = "User Details" ,
                                style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Name: ${it.getName()} ${it.getSurname()}")
                        Text("Role: ${userRole.name}")
                        Text("Email: ${it.getEmail()}")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Menu Actions
            Text(
                    text = "Available Actions:" ,
                    style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(menu.getActions().size) { index ->
                    val action = menu.getActions()[index]
                    Button(
                            onClick = {
                                handleMenuAction(
                                        action ,
                                        navController ,
                                        onNavigateToSettings
                                )
                            } ,
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                    ) {
                        Text(text = action.description)
                    }
                }
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
        "Sign out"             -> navController.navigate("login")
        "Manage Favorite Means of Transport" -> navController.navigate("favorite_transport")
        "View Routes"          -> navController.navigate("view_routes")
        "Declare Availability" -> navController.navigate("declare_availability")
        "View Reports"         -> navController.navigate("view_reports")
        "Initialize System"    -> navController.navigate("initialize_system")
        "Go to Settings"       -> onNavigateToSettings()
        else                   -> menuAction.action.invoke()
    }
}
