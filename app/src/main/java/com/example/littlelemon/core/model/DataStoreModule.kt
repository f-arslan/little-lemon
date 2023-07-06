package com.example.littlelemon.core.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

object DataStoreSingleton {
    private val Context.dataStoreInstance: DataStore<Preferences> by preferencesDataStore(name = "settings")

    fun getDataStore(context: Context): DataStore<Preferences> {
        return context.dataStoreInstance
    }
}