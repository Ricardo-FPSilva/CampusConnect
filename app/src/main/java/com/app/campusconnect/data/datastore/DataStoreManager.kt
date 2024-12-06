package com.app.campusconnect.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.app.campusconnect.models.common.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val USER_PREFERENCES = "user_preferences"
private val TOKEN_KEY = stringPreferencesKey("token")
private val USER_KEY = stringPreferencesKey("user")

class DataStoreManager(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES)
    private val gson = Gson() // Adicione uma instância do Gson

    suspend fun storeUser(user: User) {
        val userJson = gson.toJson(user) // Serializa o usuário para JSON
        context.dataStore.edit { preferences ->
            preferences[USER_KEY] = userJson
        }
    }

    fun getUser(): Flow<User?> {
        return context.dataStore.data.map { preferences ->
            val userJson = preferences[USER_KEY]
            if (userJson != null) {
                gson.fromJson(userJson, User::class.java) // Desserializa o JSON para User
            } else {
                null
            }
        }
    }

    suspend fun storeToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    fun getToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    suspend fun clearUser() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_KEY)
        }
    }

    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}