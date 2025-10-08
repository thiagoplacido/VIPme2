package com.seuapp.vipme2.presentation.products 
 
import androidx.compose.foundation.Image 
import androidx.compose.foundation.layout.* 
import androidx.compose.foundation.lazy.LazyColumn 
import androidx.compose.foundation.lazy.items 
import androidx.compose.material3.* 
import androidx.compose.runtime.* 
import androidx.compose.ui.Modifier 
import androidx.compose.ui.unit.dp 
import androidx.hilt.navigation.compose.hiltViewModel 
import coil.compose.AsyncImage 
import com.seuapp.vipme2.data.local.model.Product 
 
@Composable 
fun ProductScreen(viewModel: ProductViewModel = hiltViewModel()) { 
    val products by viewModel.products.collectAsState() 
 
    LazyColumn(modifier = Modifier.padding(16.dp)) { 
