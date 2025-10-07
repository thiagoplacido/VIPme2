package com.seuapp.vipme2.presentation.customers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seuapp.vipme2.data.local.model.Customer

@Composable
fun CustomerScreen(
    // Lógica com ViewModel será adicionada depois
) {
    val customers = emptyList<Customer>() // Dados de exemplo

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Clientes", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* TODO */ }) {
            Text("Adicionar Cliente")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(customers) { customer ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(customer.name, style = MaterialTheme.typography.titleMedium)
                        Text("Telefone: ${customer.phone ?: "-"}")
                        Text("Email: ${customer.email ?: "-"}")
                    }
                }
            }
        }
    }
}