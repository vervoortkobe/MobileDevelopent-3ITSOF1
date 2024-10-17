package edu.ap.userdetails

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        val NAME_KEY = stringPreferencesKey("user_name")
        val EMAIL_KEY = stringPreferencesKey("email")
        val PASSWORD_KEY = stringPreferencesKey("password")
    }

    val userName: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[NAME_KEY] ?: "NO_NAME"
    }

    val email: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[EMAIL_KEY] ?: "NO_EMAIL"
    }

    val password: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[PASSWORD_KEY] ?: "NO_PASSWORD"
    }

    suspend fun saveUser(name: String, email: String, password: String) {
        context.dataStore.edit { preferences ->
            preferences[NAME_KEY] = name
            preferences[EMAIL_KEY] = email
            preferences[PASSWORD_KEY] = password
        }
    }
}
