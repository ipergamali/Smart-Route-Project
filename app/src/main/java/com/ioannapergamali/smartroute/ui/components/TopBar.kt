package com.ioannapergamali.smartroute.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material.icons.filled.BugReport

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
        title : String ,
        navController : NavController ,
        showBackButton : Boolean = true // Προαιρετική εμφάνιση του κουμπιού πίσω
)
{
    TopAppBar(
            title = { Text(title) } ,
            navigationIcon = if (showBackButton)
            {
                {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack ,
                                contentDescription = "Back"
                        )
                    }
                }
            }
            else ({
                // Άδειος Composable
            }) ,
            actions = {
                // Debug Button in the Side Menu
                IconButton(onClick = { navController.navigate("debug") }) {
                    Icon(Icons.Default.BugReport , contentDescription = "Debug")
                }
            } ,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary ,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
    )
}
