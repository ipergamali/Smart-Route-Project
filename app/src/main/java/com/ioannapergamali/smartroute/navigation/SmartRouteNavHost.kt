package com.ioannapergamali.smartroute.navigation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ioannapergamali.smartroute.model.Role
import com.ioannapergamali.smartroute.ui.screens.LoginScreen
import com.ioannapergamali.smartroute.ui.screens.MenuScreen
import com.ioannapergamali.smartroute.ui.screens.SettingsScreen
import com.ioannapergamali.smartroute.ui.screens.SignUpScreen
import com.ioannapergamali.smartroute.ui.screens.SplashScreen
import com.ioannapergamali.smartroute.utils.UserSession
import com.ioannapergamali.smartroute.viewmodel.SettingsViewModel

@Composable
fun SmartRouteNavHost(navController: NavHostController) {
    val context = LocalContext.current
    val lastBackPressTime = remember { mutableStateOf(0L) }
    val settingsViewModel : SettingsViewModel = viewModel()

    // Handle the back press to exit the app
    BackHandler {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        if (currentRoute != "splash") {
            navController.popBackStack()
        } else {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastBackPressTime.value < 2000)
            {
                // Exit app
            }
            else
            {
                lastBackPressTime.value = currentTime
                Toast.makeText(context , "Press again to exit" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashScreen(
                    navController = navController,
                    onNavigateToLogin = {
                        navController.navigate("login")
                    },
                    onNavigateToSignUp = {
                        navController.navigate("signup")
                    }
            )
        }

        composable("login") {
            LoginScreen(
                    navController = navController ,
                    onLoginSuccess = {
                        navController.navigate("menu") {
                            popUpTo("login") { inclusive = true }
                        }
                    } ,
                    onLoginFailure = { errorMessage ->
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    },
                    onNavigateToSettings = {
                        navController.navigate("settings")
                    }
            )
        }


        composable("settings") {
            val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState()

            SettingsScreen(
                    navController = navController,
                    isDarkTheme = isDarkTheme ,
                    onThemeChange = { isDarkMode ->
                        settingsViewModel.toggleTheme(isDarkMode)
                    }
            )
        }


        composable("signup") {
            SignUpScreen(
                    navController = navController,
                    onSignUpSuccess = {
                        navController.navigate("menu") {
                            popUpTo("signup") { inclusive = true }
                        }
                    },
                    onSignUpFailure = { errorMessage ->
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }
            )
        }

        composable("menu") {
            val user = UserSession.currentUser
            val userRole = user?.getRole() ?: Role.PASSENGER

            MenuScreen(
                    user = user,
                    userRole = userRole,
                    navController = navController,
                    onNavigateToSettings = {
                        navController.navigate("settings")
                    }
            )
        }
    }
}
