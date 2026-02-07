package com.example.jcdatastore

import android.content.Context
import androidx.core.util.Consumer
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlin.collections.get
import kotlin.text.isEmpty
import kotlin.text.split

class DataStoreSource(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DS_STORE)

    suspend fun getFirstTime(): Boolean {
        val firsTime = getPreference()[FIRST_TIME]
        return firsTime ?: true
    }

    suspend fun saveFirstTime(){
        context.dataStore.edit { preferences ->
            preferences[FIRST_TIME] = false
        }
    }

    suspend fun getUsername(): String{
        return getPreference()[USERNAME] ?: ""
    }

    suspend fun saveUsername(username: String){
        context.dataStore.edit { preferences ->
            preferences[FIRST_TIME] = false
            preferences[USERNAME] = username
        }
    }

    suspend fun addCountry(country: String){
        context.dataStore.edit { preferences ->
            val currentCountries = getRawCountries()
            val newCountries = if (currentCountries.isEmpty()) country else "$currentCountries,$country"
            preferences[COUNTRIES] = newCountries
        }
    }

    suspend fun getRawCountries() = getPreference()[COUNTRIES] ?: ""

    suspend fun getCountries(): List<String>{
        val rawCountries = getRawCountries()
        val countries = rawCountries.split(",")
        return countries
    }

    private suspend fun getPreference() = context.dataStore.data.first()

    companion object{
        private const val DS_STORE = "data_store_app"
        private val FIRST_TIME = booleanPreferencesKey("k_first_time")
        private val COUNTRIES = stringPreferencesKey("k_countries")
        private val USERNAME = stringPreferencesKey("k_username")
    }
}