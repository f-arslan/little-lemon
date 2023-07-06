package com.example.littlelemon.ui.screens.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.RequestState
import com.example.littlelemon.core.Network.Companion.URL
import com.example.littlelemon.core.data.Menu
import com.example.littlelemon.core.data.MenuCategory
import com.example.littlelemon.core.data.MenuNetwork
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }
    private val _allDishes = MutableStateFlow<RequestState<List<MenuNetwork>>>(RequestState.Idle)
    val allDishes = _allDishes.asStateFlow()

    private val _allDishesWithPhrase = MutableStateFlow<List<MenuNetwork>>(emptyList())
    val allDishesWithPhrase = _allDishesWithPhrase.asStateFlow()

    private val _phrase = MutableStateFlow("")
    val phrase = _phrase.asStateFlow()

    private val _selectedMenu = MutableStateFlow<Menu?>(null)
    val selectedMenu = _selectedMenu.asStateFlow()

    private val filteredDishesFlow =
        combine(_allDishes, _phrase, _selectedMenu) { allDishesState, phrase, selectedMenu ->
            if (allDishesState is RequestState.Success) {
                var filteredDishes = allDishesState.data
                if (phrase.isNotBlank()) {
                    filteredDishes = filteredDishes.filter {
                        it.title.contains(phrase, true)
                    }
                }
                if (selectedMenu != null) {
                    filteredDishes = filteredDishes.filter {
                        it.category == selectedMenu.menuName.lowercase()
                    }
                }
                filteredDishes
            } else {
                emptyList()
            }
        }

    init {
        viewModelScope.launch {
            filteredDishesFlow.collect { filteredDishes ->
                _allDishesWithPhrase.value = filteredDishes
            }
        }
    }

    fun getAllDishes() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = httpClient.get(URL).body<MenuCategory>().menu
            _allDishes.value = RequestState.Success(response)
        }
    }

    fun onPhraseChanged(newPhrase: String) {
        _phrase.value = newPhrase
    }

    fun onMenuChanged(menu: Menu) {
        _phrase.value = ""
        if (_selectedMenu.value == menu) {
            _selectedMenu.value = null
            return
        }
        _selectedMenu.value = menu
    }
}