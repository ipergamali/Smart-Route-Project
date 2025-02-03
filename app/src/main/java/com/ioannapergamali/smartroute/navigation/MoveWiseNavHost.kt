package com.ioannapergamali.movewise.navigation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ioannapergamali.movewise.ui.screens.LoginScreen
import com.ioannapergamali.movewise.ui.screens.MenuScreen
import com.ioannapergamali.movewise.ui.screens.SettingsScreen
import com.ioannapergamali.movewise.ui.screens.SignUpScreen
import com.ioannapergamali.movewise.ui.screens.SplashScreen
import com.ioannapergamali.movewise.utils.UserSession

@Composable
fun MoveWiseNavHost(navController: NavHostController) {
    val context = LocalContext.current

    // Handle the back press to exit the app
    BackHandler {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        if (currentRoute != "splash") {
            navController.popBackStack()
        } else {
            Toast.makeText(
                context,
                "Press again to exit",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashScreen(
                navController = navController,
                onNavigateToLogin = {
                    navController.navigate("login")
                },
                onNavigateToSettings = {
                    navController.navigate("signup")
                }
            )
        }
        // Login Screen
        composable("login") {
            LoginScreen(
                navController = navController,
                onLoginSuccess = {
                    navController.navigate("menu") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onLoginFailure = { errorMessage ->
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                },
                onNavigateToSettings = {
                    navController.navigate("settings")
                }
            )
        }

        // Settings Screen
        composable("settings") {
            SettingsScreen(
                navController = navController,
                onThemeChange = { isDarkMode ->
                    // Handle theme change logic here
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


        // Menu Screen (for authenticated users)
        composable("menu") {
            val user = UserSession.currentUser // Get the user from the session
            val userRole = user?.getRole() ?: "Passenger" // Default to "Passenger" if null

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
