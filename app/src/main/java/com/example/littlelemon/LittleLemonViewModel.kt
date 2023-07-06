package com.example.littlelemon

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.core.model.DataStoreSingleton
import com.example.littlelemon.ui.screens.onboarding_screen.OnboardingScreenViewModel
import com.example.littlelemon.ui.screens.onboarding_screen.AppUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LittleLemonViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<RequestState<AppUiState>>(RequestState.Idle)
    val uiState: StateFlow<RequestState<AppUiState>> = _uiState.asStateFlow()

    fun getData(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            DataStoreSingleton.getDataStore(context).data.map { preferences ->
                AppUiState(
                    preferences[OnboardingScreenViewModel.FIRST_NAME_KEY] ?: "",
                    preferences[OnboardingScreenViewModel.LAST_NAME_KEY] ?: "",
                    preferences[OnboardingScreenViewModel.EMAIL_KEY] ?: ""
                )
            }.collect {
                _uiState.value = RequestState.Success(it)
            }
        }
    }
}