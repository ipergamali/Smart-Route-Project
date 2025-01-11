package com.ioannapergamali.smartroute

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ioannapergamali.smartroute.navigation.SmartRouteNavHost
import com.ioannapergamali.smartroute.ui.theme.SmartRouteTheme
import com.ioannapergamali.smartroute.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            val navController : NavHostController = rememberNavController()
            val settingsViewModel : SettingsViewModel = viewModel()

            val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState()

            SmartRouteTheme(darkTheme = isDarkTheme) {
                SmartRouteNavHost(navController = navController)
            }
        }
    }
}
