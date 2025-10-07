package com.seuapp.vipme2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.seuapp.vipme2.presentation.dashboard.DashboardScreen
import com.seuapp.vipme2.presentation.customers.CustomerScreen
import com.seuapp.vipme2.presentation.finance.FinanceChartScreen
import com.seuapp.vipme2.presentation.lock.LockScreen
import com.seuapp.vipme2.presentation.products.ProductScreen
import com.seuapp.vipme2.presentation.splash.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Lock : Screen("lock")
    object Dashboard : Screen("dashboard")
    object Customers : Screen("customers")
    object Products : Screen("products")
    object Finance : Screen("finance")
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToDashboard = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToLock = {
                    navController.navigate(Screen.Lock.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Lock.route) {
            LockScreen(
                onPurchase = { /* TODO: Implementar fluxo de compra */ },
                onRestore = { /* TODO: Implementar fluxo de restauração */ }
            )
        }

        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToCustomers = { navController.navigate(Screen.Customers.route) },
                onNavigateToProducts = { navController.navigate(Screen.Products.route) },
                onNavigateToFinance = { navController.navigate(Screen.Finance.route) }
            )
        }

        composable(Screen.Customers.route) {
            CustomerScreen()
        }

        composable(Screen.Products.route) {
            ProductScreen()
        }

        composable(Screen.Finance.route) {
            FinanceChartScreen()
        }
    }
}