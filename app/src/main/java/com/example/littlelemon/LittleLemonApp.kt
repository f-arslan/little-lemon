package com.example.littlelemon

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.graph.HOME_SCREEN
import com.example.littlelemon.ui.graph.ONBOARDING_SCREEN
import com.example.littlelemon.ui.graph.appGraph
import com.example.littlelemon.ui.screens.onboarding_screen.AppUiState

@Composable
fun LittleLemonApp(viewModel: LittleLemonViewModel = viewModel()) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getData(context)
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (val result = uiState) {
        is RequestState.Success -> {
            LittleLemonAppScreen(result.data)
        }

        else -> {}
    }

}

@Composable
fun LittleLemonAppScreen(data: AppUiState) {
    val navController = rememberNavController()
    val startDestination = if (data.email.isEmpty()) {
        ONBOARDING_SCREEN
    } else {
        HOME_SCREEN
    }
    Scaffold {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = startDestination
        ) {
            appGraph(navController)
        }
    }
}