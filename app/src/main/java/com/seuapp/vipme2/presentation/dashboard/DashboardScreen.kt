package com.seuapp.vipme2.presentation.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen(
    onNavigateToCustomers: () -> Unit,
    onNavigateToProducts: () -> Unit,
    onNavigateToFinance: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("VIPme2", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Atalhos do Dashboard
        Button(onClick = onNavigateToCustomers, modifier = Modifier.fillMaxWidth()) {
            Text("Gerenciar Clientes")
        }
        Button(onClick = onNavigateToProducts, modifier = Modifier.fillMaxWidth()) {
            Text("Gerenciar Produtos")
        }
        Button(onClick = onNavigateToFinance, modifier = Modifier.fillMaxWidth()) {
            Text("Ver Fluxo de Caixa")
        }

        // Cards de Resumo (serão dinâmicos com ViewModel no futuro)
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Resumo Financeiro", style = MaterialTheme.typography.titleMedium)
                Text("Entradas: R$ 0,00")
                Text("Saídas: R$ 0,00")
                Text("Saldo: R$ 0,00")
            }
        }
    }
}