package com.example.littlelemon.ui.screens.onboarding_screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.littlelemon.common.composables.ButtonMaxWidth
import com.example.littlelemon.common.composables.LittleLemonTopBar
import com.example.littlelemon.common.composables.TextFieldWithLabel
import com.example.littlelemon.util.Constants.HIGH_PADDING
import com.example.littlelemon.util.Constants.MAX_PADDING
import com.example.littlelemon.util.Constants.VERY_HIGH_PADDING


@Composable
fun OnboardingScreenProvider(viewModel: OnboardingScreenViewModel = viewModel()) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    OnboardingScreen(
        uiState,
        viewModel::onFirstNameChange,
        viewModel::onLastNameChange,
        viewModel::onEmailChange,
        buttonOnClick = {
            viewModel.onRegisterClick(context)
        }
    )
}

@Composable
fun OnboardingScreen(
    uiState: AppUiState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    buttonOnClick: () -> Unit
) {
    Scaffold(
        topBar = {
            LittleLemonTopBar()
        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(VERY_HIGH_PADDING)) {
                ButtonMaxWidth(text = "Register", onClick = buttonOnClick)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(HIGH_PADDING)
        ) {
            Spacer(modifier = Modifier.height(VERY_HIGH_PADDING))
            Text(text = "Personal Information", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(MAX_PADDING))
            TextFieldWithLabel(
                label = "First Name",
                textFieldText = uiState.firstName,
                onTextChange = onFirstNameChange
            )
            Spacer(modifier = Modifier.height(HIGH_PADDING))
            TextFieldWithLabel(
                label = "Last Name",
                textFieldText = uiState.lastName,
                onTextChange = onLastNameChange
            )
            Spacer(modifier = Modifier.height(HIGH_PADDING))
            TextFieldWithLabel(
                label = "Email",
                textFieldText = uiState.email,
                onTextChange = onEmailChange
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(
        uiState = AppUiState(),
        onFirstNameChange = {},
        onLastNameChange = {},
        onEmailChange = {},
        {}
    )
}