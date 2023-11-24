package com.nsadisha.android.ntask.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nsadisha.android.ntask.navigation.AppRoutes
import com.nsadisha.android.ntask.vm.OnboardingViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    vm: OnboardingViewModel = viewModel()
) {
    val fName = vm.readName().split(" ")[0]
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Hi $fName")
    }
}