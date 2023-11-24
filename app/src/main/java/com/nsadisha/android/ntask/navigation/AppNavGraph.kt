package com.nsadisha.android.ntask.navigation

import SetupScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nsadisha.android.ntask.ui.screens.HomeScreen
import com.nsadisha.android.ntask.ui.screens.SettingsScreen
import com.nsadisha.android.ntask.ui.screens.TasksScreen
import com.nsadisha.android.ntask.ui.screens.onboarding.WelcomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = AppRoutes.WELCOME_SCREEN.name){
        composable(AppRoutes.HOME_SCREEN.name) { HomeScreen(navController) }
        composable(AppRoutes.SETTINGS_SCREEN.name) { SettingsScreen(navController) }
        composable(AppRoutes.TASKS_SCREEN.name) { TasksScreen(navController) }
        composable(AppRoutes.WELCOME_SCREEN.name) { WelcomeScreen(navController) }
        composable(AppRoutes.SETUP_SCREEN.name) { SetupScreen(navController) }
    }
}