package com.seuapp.vipme2.presentation.lock

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.seuapp.vipme2.presentation.license.LicenseViewModel

@Composable
fun LockScreen(
    viewModel: LicenseViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Seu período de teste terminou",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Para continuar usando o VIPme2, por favor, adquira a versão premium.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { viewModel.launchPurchaseFlow(activity) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Comprar agora")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { viewModel.restorePurchases() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Restaurar compra")
            }
        }
    }
}