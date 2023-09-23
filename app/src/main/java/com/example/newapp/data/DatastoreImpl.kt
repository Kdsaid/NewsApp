package com.example.newapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DatastoreImpl @Inject constructor(
    val context: Context
) {

    suspend fun write(key: String, value: String) {
        context.dataStore.edit { settings ->
            settings[stringPreferencesKey(key)] = value
        }
    }

    suspend fun read(key: String, defaultValue: String): String {
        return context.dataStore.data.map { settings ->
            settings[stringPreferencesKey(key)] ?: defaultValue
        }.first().toString()
    }


    suspend fun write(key: String, values: List<String>) {
        val serializedList = Gson().toJson(values)
        context.dataStore.edit { settings ->
            settings[stringPreferencesKey(key)] = serializedList
        }
    }

    suspend fun read(key: String): List<String> {
        val serializedList = context.dataStore.data.map { settings ->
            settings[stringPreferencesKey(key)]
        }.firstOrNull()

        return if (serializedList != null) {
            Gson().fromJson(serializedList, object : TypeToken<List<String>>() {}.type)
        } else {
            emptyList()
        }
    }

    suspend fun write(key: String, value: Int) {
        context.dataStore.edit { settings ->
            settings[intPreferencesKey(key)] = value
        }
    }

    suspend fun read(key: String, defaultValue: Int): Int {
        return context.dataStore.data.map { settings ->
            settings[intPreferencesKey(key)] ?: defaultValue
        }.first().toInt()
    }

    suspend fun write(key: String, value: Double) {
        context.dataStore.edit { settings ->
            settings[doublePreferencesKey(key)] = value
        }
    }

    suspend fun read(key: String, defaultValue: Double): Double {
        return context.dataStore.data.map { settings ->
            settings[doublePreferencesKey(key)] ?: defaultValue
        }.first().toDouble()

    }

    suspend fun write(key: String, value: Boolean) {
        context.dataStore.edit { settings ->
            settings[booleanPreferencesKey(key)] = value
        }
    }

    suspend fun read(key: String, defaultValue: Boolean): Boolean {
        return context.dataStore.data.map { settings ->
            settings[booleanPreferencesKey(key)] ?: defaultValue
        }.first()

    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }

}

suspend fun Context.read(key: String, defaultValue: Boolean): Boolean {
    return dataStore.data.map { settings ->
        settings[booleanPreferencesKey(key)] ?: defaultValue
    }.first()

}