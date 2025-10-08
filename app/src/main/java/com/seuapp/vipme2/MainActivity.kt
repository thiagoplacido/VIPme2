package com.seuapp.vipme2 
 
import android.os.Bundle 
import androidx.activity.ComponentActivity 
import androidx.activity.compose.setContent 
import androidx.compose.material3.* 
import androidx.compose.runtime.Composable 
import androidx.navigation.compose.NavHost 
import androidx.navigation.compose.composable 
import androidx.navigation.compose.rememberNavController 
import com.seuapp.vipme2.presentation.products.ProductScreen 
import com.seuapp.vipme2.presentation.customers.CustomerScreen 
import com.seuapp.vipme2.presentation.sales.SaleScreen 
import com.seuapp.vipme2.presentation.cash.CashEntryScreen 
import com.seuapp.vipme2.presentation.stock.StockMovementScreen 
import dagger.hilt.android.AndroidEntryPoint 
 
@AndroidEntryPoint 
class MainActivity : ComponentActivity() { 
    override fun onCreate(savedInstanceState: Bundle?) { 
        super.onCreate(savedInstanceState) 
        setContent { VipmeApp() } 
    } 
} 
 
@Composable 
fun VipmeApp() { 
    val navController = rememberNavController() 
    NavHost(navController = navController, startDestination = "products") { 
        composable("products") { ProductScreen() } 
        composable("customers") { CustomerScreen() } 
        composable("sales") { SaleScreen() } 
        composable("cash") { CashEntryScreen() } 
        composable("stock") { StockMovementScreen() } 
    } 
} 
