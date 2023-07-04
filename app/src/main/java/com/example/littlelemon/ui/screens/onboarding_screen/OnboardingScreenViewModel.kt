package com.example.littlelemon.ui.screens.onboarding_screen

import android.content.Context
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.common.ext.isValidEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AppUiState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = ""
)

class OnboardingScreenViewModel() : ViewModel() {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()


    private val firstName
        get() = uiState.value.firstName.trim()

    private val lastName
        get() = uiState.value.lastName.trim()

    private val email
        get() = uiState.value.email.trim()


    fun onFirstNameChange(newValue: String) {
        _uiState.update {
            it.copy(firstName = newValue)
        }
    }

    fun onLastNameChange(newValue: String) {
        _uiState.update {
            it.copy(lastName = newValue)
        }
    }

    fun onEmailChange(newValue: String) {
        _uiState.update {
            it.copy(email = newValue)
        }
    }

    fun onRegisterClick(context: Context) {
        if (firstName.isBlank()) {
            Toast.makeText(context, "First name cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        if (lastName.isBlank()) {
            Toast.makeText(context, "Last name cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        if (email.isBlank()) {
            Toast.makeText(context, "Email cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        if (!email.isValidEmail()) {
            Toast.makeText(context, "Email is not valid", Toast.LENGTH_SHORT).show()
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            saveToDataStore(context)
        }
    }

    private suspend fun saveToDataStore(context: Context) {
        context.dataStore.edit { settings ->
            settings[FIRST_NAME_KEY] = firstName
            settings[LAST_NAME_KEY] = lastName
            settings[EMAIL_KEY] = email
        }
    }



    companion object {
        val FIRST_NAME_KEY = stringPreferencesKey("first_name")
        val LAST_NAME_KEY = stringPreferencesKey("last_name")
        val EMAIL_KEY = stringPreferencesKey("email")
    }

}