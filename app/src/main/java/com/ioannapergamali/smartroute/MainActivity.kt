package com.ioannapergamali.smartroute

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ioannapergamali.smartroute.navigation.SmartRouteNavHost
import com.ioannapergamali.smartroute.ui.theme.SmartRouteTheme


class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            SmartRouteTheme {
                val navController = rememberNavController()
                SmartRouteNavHost(navController = navController)
            }
        }
    }
}
