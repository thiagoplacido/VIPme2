package com.seuapp.vipme2.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.seuapp.vipme2.data.repository.LicenseStatus
import com.seuapp.vipme2.presentation.license.LicenseViewModel

@Composable
fun SplashScreen(
    viewModel: LicenseViewModel = hiltViewModel(),
    onNavigateToDashboard: () -> Unit,
    onNavigateToLock: () -> Unit
) {
    val licenseStatus by viewModel.licenseStatus.collectAsState()

    LaunchedEffect(licenseStatus) {
        when (licenseStatus) {
            is LicenseStatus.Trial, LicenseStatus.Premium -> {
                onNavigateToDashboard()
            }
            is LicenseStatus.TrialExpired -> {
                onNavigateToLock()
            }
            is LicenseStatus.Unknown -> {
                // Aguarda o status ser determinado
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}