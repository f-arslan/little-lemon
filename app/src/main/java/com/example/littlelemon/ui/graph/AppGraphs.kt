package com.example.littlelemon.ui.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.littlelemon.ui.screens.home_screen.HomeScreenProvider
import com.example.littlelemon.ui.screens.onboarding_screen.OnboardingScreenProvider
import com.example.littlelemon.ui.screens.profile_screen.ProfileScreenProvider

fun NavGraphBuilder.appGraph(navController: NavController) {
    composable(ONBOARDING_SCREEN) {
        OnboardingScreenProvider()
    }
    composable(HOME_SCREEN) {
        HomeScreenProvider {
            navController.navigate(PROFILE_SCREEN)
        }
    }
    composable(PROFILE_SCREEN) {
        ProfileScreenProvider()
    }
}