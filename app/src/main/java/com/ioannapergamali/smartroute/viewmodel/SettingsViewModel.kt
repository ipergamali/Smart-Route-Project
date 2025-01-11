package com.ioannapergamali.smartroute.viewmodel

import android.app.Application
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

// Extension για DataStore
private val Application.dataStore by preferencesDataStore(name = "settings")

class SettingsViewModel(application : Application) : AndroidViewModel(application)
{

    private val dataStore = application.dataStore

    // Key για το Dark Theme
    private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")

    // Κατάσταση του θέματος
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme : StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    init
    {
        // Φόρτωση της αποθηκευμένης κατάστασης
        viewModelScope.launch {
            dataStore.data
                    .catch { exception ->
                        // Σε περίπτωση σφάλματος, δώσε την προεπιλεγμένη τιμή
                        if (exception is Exception) emit(emptyPreferences())
                    }
                    .map { preferences ->
                        preferences[DARK_THEME_KEY] ?: false // Προεπιλογή: Light Theme
                    }
                    .collect {
                        _isDarkTheme.value = it
                    }
        }
    }

    // Εναλλαγή θέματος και αποθήκευση
    fun toggleTheme(isDark : Boolean)
    {
        viewModelScope.launch {
            _isDarkTheme.value = isDark
            dataStore.edit { preferences ->
                preferences[DARK_THEME_KEY] = isDark
            }
        }
    }
}
