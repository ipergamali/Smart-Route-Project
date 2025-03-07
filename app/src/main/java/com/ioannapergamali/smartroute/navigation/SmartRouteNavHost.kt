package com.ioannapergamali.smartroute.navigation

import android.app.Activity
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
import com.ioannapergamali.smartroute.ui.screens.DebugScreen
import com.ioannapergamali.smartroute.ui.screens.DeclareAvailabilityScreen
import com.ioannapergamali.smartroute.ui.screens.LoginScreen
import com.ioannapergamali.smartroute.ui.screens.MenuScreen
import com.ioannapergamali.smartroute.ui.screens.SettingsScreen
import com.ioannapergamali.smartroute.ui.screens.SignUpScreen
import com.ioannapergamali.smartroute.ui.screens.SplashScreen
import com.ioannapergamali.smartroute.ui.screens.ViewReportsScreen
import com.ioannapergamali.smartroute.ui.screens.ViewRoutesScreen
import com.ioannapergamali.smartroute.utils.UserSession
import com.ioannapergamali.smartroute.viewmodel.SettingsViewModel

@Composable
fun SmartRouteNavHost(navController: NavHostController) {
    val context = LocalContext.current
    val lastBackPressTime = remember { mutableStateOf(0L) }
    val settingsViewModel : SettingsViewModel = viewModel()

    BackHandler {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        if (currentRoute != "splash") {
            navController.popBackStack()
        } else {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastBackPressTime.value < 2000)
            {
                (context as? Activity)?.finish()
            }
            else
            {
                lastBackPressTime.value = currentTime
                Toast.makeText(context , "Press again to exit" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    val startDestination = if (UserSession.isLoggedIn) "menu" else "splash"

    NavHost(navController = navController , startDestination = startDestination) {

        composable("splash") {
            SplashScreen(
                    navController = navController,
                    onNavigateToLogin = { navController.navigate("login") } ,
                    onNavigateToSignUp = { navController.navigate("signup") }
            )
        }

        composable("view_routes") {
            ViewRoutesScreen(navController)
        }

        composable("declare_availability") {
            DeclareAvailabilityScreen(navController)
        }

        composable("view_reports") {
            ViewReportsScreen(navController)
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

        composable("settings/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: "Unknown"
            val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState()

            SettingsScreen(
                    navController = navController ,
                    email = email ,
                    isDarkTheme = isDarkTheme ,
                    onThemeChange = { settingsViewModel.toggleTheme(it) }
            )
        }

        composable("menu") {
            val user = UserSession.currentUser
            val userRole = user?.getRole()?.name ?: Role.PASSENGER.name  // ✅ Μετατροπή σε String

            if (user == null) {
                navController.navigate("login") {
                    popUpTo("menu") { inclusive = true }
                }
            } else {
                MenuScreen(
                    user = user,
                    userRole = userRole,  // ✅ Τώρα είναι τύπου String
                    navController = navController,
                    onNavigateToSettings = {
                        navController.navigate("settings/${user.getEmail()}")
                    }
                )
            }
        }

        composable("debug") {
            DebugScreen()
        }


    }

}
