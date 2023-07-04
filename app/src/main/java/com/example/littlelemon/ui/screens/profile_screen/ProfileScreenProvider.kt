package com.example.littlelemon.ui.screens.profile_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.littlelemon.RequestState
import com.example.littlelemon.common.composables.ButtonMaxWidth
import com.example.littlelemon.common.composables.LittleLemonTopBar
import com.example.littlelemon.common.composables.TextFieldWithLabel
import com.example.littlelemon.ui.screens.onboarding_screen.AppUiState
import com.example.littlelemon.util.Constants

@Composable
fun ProfileScreenProvider(viewModel: ProfileScreenViewModel = viewModel()) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.getData(context)
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (val result = uiState) {
        is RequestState.Success -> {
            ProfileScreen(result.data) {
                viewModel.resetDataStore(context)
            }
        }
        else -> {}
    }
}

@Composable
fun ProfileScreen(uiState: AppUiState, buttonOnClick: () -> Unit) {
    Scaffold(
        topBar = {
            LittleLemonTopBar(isProfileScreen = true)
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Constants.VERY_HIGH_PADDING)
            ) {
                ButtonMaxWidth(text = "Log out", onClick = buttonOnClick)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(Constants.HIGH_PADDING)
        ) {
            Spacer(modifier = Modifier.height(Constants.VERY_HIGH_PADDING))
            Text(text = "Personal Information", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(Constants.MAX_PADDING))
            TextFieldWithLabel(
                label = "First Name",
                textFieldText = uiState.firstName,
                readOnly = true
            )
            Spacer(modifier = Modifier.height(Constants.HIGH_PADDING))
            TextFieldWithLabel(
                label = "Last Name",
                textFieldText = uiState.lastName,
                readOnly = true
            )
            Spacer(modifier = Modifier.height(Constants.HIGH_PADDING))
            TextFieldWithLabel(
                label = "Email",
                textFieldText = uiState.email,
                readOnly = true
            )
        }
    }
}