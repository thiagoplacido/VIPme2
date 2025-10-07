package com.seuapp.vipme2.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

// Define os diferentes estados de licença
sealed class LicenseStatus {
    object Unknown : LicenseStatus()
    data class Trial(val daysRemaining: Long) : LicenseStatus()
    object TrialExpired : LicenseStatus()
    object Premium : LicenseStatus()
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "vipme2_license_prefs"
)

@Singleton
class LicenseRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferencesKeys {
        val TRIAL_START_DATE = longPreferencesKey("trial_start_date")
        val IS_PREMIUM = booleanPreferencesKey("is_premium")
    }

    val licenseStatus: Flow<LicenseStatus> = context.dataStore.data
        .map { preferences ->
            val isPremium = preferences[PreferencesKeys.IS_PREMIUM] ?: false
            if (isPremium) {
                return@map LicenseStatus.Premium
            }

            val trialStartDate = preferences[PreferencesKeys.TRIAL_START_DATE]
            if (trialStartDate == null) {
                // Se a data de início do trial não existe, inicia um novo trial.
                startTrial()
                return@map LicenseStatus.Trial(TRIAL_DURATION_DAYS)
            }

            val elapsedTime = System.currentTimeMillis() - trialStartDate
            val elapsedDays = TimeUnit.MILLISECONDS.toDays(elapsedTime)

            if (elapsedDays >= TRIAL_DURATION_DAYS) {
                LicenseStatus.TrialExpired
            } else {
                LicenseStatus.Trial(TRIAL_DURATION_DAYS - elapsedDays)
            }
        }

    private suspend fun startTrial() {
        context.dataStore.edit {
            val trialStartDate = it[PreferencesKeys.TRIAL_START_DATE]
            if (trialStartDate == null) { // Só define se ainda não existir
                it[PreferencesKeys.TRIAL_START_DATE] = System.currentTimeMillis()
            }
        }
    }

    suspend fun activatePremium() {
        context.dataStore.edit {
            it[PreferencesKeys.IS_PREMIUM] = true
        }
    }

    companion object {
        const val TRIAL_DURATION_DAYS = 7L
    }
}