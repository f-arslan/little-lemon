package com.example.littlelemon.ui.screens.profile_screen

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.RequestState
import com.example.littlelemon.ui.screens.onboarding_screen.AppUiState
import com.example.littlelemon.ui.screens.onboarding_screen.OnboardingScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProfileScreenViewModel : ViewModel() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val _uiState = MutableStateFlow<RequestState<AppUiState>>(RequestState.Idle)
    val uiState: StateFlow<RequestState<AppUiState>> = _uiState.asStateFlow()

    fun getData(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            context.dataStore.data.map { preferences ->
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

    fun resetDataStore(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            context.dataStore.edit { preferences ->
                preferences.clear()
            }
        }
    }
}