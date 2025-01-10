package com.ioannapergamali.movewise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ioannapergamali.movewise.navigation.MoveWiseNavHost
import com.ioannapergamali.movewise.ui.theme.MoveWiseTheme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            MoveWiseTheme {
                val navController = rememberNavController()
                MoveWiseNavHost(navController = navController)
            }
        }
    }
}
