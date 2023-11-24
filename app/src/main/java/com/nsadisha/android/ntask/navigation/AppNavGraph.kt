package com.nsadisha.android.ntask.navigation

import com.nsadisha.android.ntask.ui.screens.onboarding.SetupScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nsadisha.android.ntask.ui.screens.HomeScreenHost
import com.nsadisha.android.ntask.ui.screens.onboarding.WelcomeScreen
import com.nsadisha.android.ntask.vm.OnboardingViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    vm: OnboardingViewModel = viewModel()
) {
    val startDestination: String = when(vm.isRegistered()){
        true -> AppRoutes.HOME_SCREEN_HOST.name
        else -> AppRoutes.WELCOME_SCREEN.name
    }

    NavHost(navController = navController, startDestination = startDestination){
        composable(AppRoutes.HOME_SCREEN_HOST.name) { HomeScreenHost() }
        composable(AppRoutes.WELCOME_SCREEN.name) { WelcomeScreen(navController) }
        composable(AppRoutes.SETUP_SCREEN.name) { SetupScreen(navController) }
    }
}