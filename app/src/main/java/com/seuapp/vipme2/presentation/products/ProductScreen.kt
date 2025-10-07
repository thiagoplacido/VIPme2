package com.seuapp.vipme2.presentation.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.seuapp.vipme2.data.local.model.Product

@Composable
fun ProductScreen() {
    val viewModel: ProductViewModel = hiltViewModel()
    val products = viewModel.products

    Column {
        Text(text = "Produtos disponíveis:")
        products.forEach {
            Text(text = it)

) {
    val products by viewModel.products.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Produtos", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* TODO: Add product */ }) {
            Text("Adicionar Produto")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(products) { product ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.padding(12.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(product.imageUrl),
                            contentDescription = product.name,
                            modifier = Modifier.size(64.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(product.name, style = MaterialTheme.typography.titleMedium)
                            Text("Preço: R$ ${product.price}")
                            Text(product.description ?: "")
                        }
                    }
                }
            }
        }
    }
}