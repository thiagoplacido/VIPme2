@echo off
SET ROOT=C:\Users\Thiago\AndroidStudioProjects\VIPme2\app\src\main\java\com\seuapp\vipme2

echo 🔧 Criando estrutura de pastas...

mkdir "%ROOT%\data\local"
mkdir "%ROOT%\data\remote"
mkdir "%ROOT%\data\mock"
mkdir "%ROOT%\di"
mkdir "%ROOT%\domain"
mkdir "%ROOT%\presentation\products"
mkdir "%ROOT%\presentation\customers"
mkdir "%ROOT%\presentation\sales"
mkdir "%ROOT%\presentation\cash"
mkdir "%ROOT%\presentation\stock"
mkdir "%ROOT%\..\..\..\assets"

echo ✅ Pastas criadas!

REM 🔹 Use Cases
SET DOMAIN=%ROOT%\domain

echo package com.seuapp.vipme2.domain > "%DOMAIN%\GetProductsUseCase.kt"
echo import com.seuapp.vipme2.data.local.model.Product >> "%DOMAIN%\GetProductsUseCase.kt"
echo import com.seuapp.vipme2.data.remote.RemoteRepository >> "%DOMAIN%\GetProductsUseCase.kt"
echo import javax.inject.Inject >> "%DOMAIN%\GetProductsUseCase.kt"
echo class GetProductsUseCase @Inject constructor(private val remoteRepository: RemoteRepository) { >> "%DOMAIN%\GetProductsUseCase.kt"
echo     suspend operator fun invoke(): List<Product> = remoteRepository.fetchProducts() >> "%DOMAIN%\GetProductsUseCase.kt"
echo } >> "%DOMAIN%\GetProductsUseCase.kt"

echo package com.seuapp.vipme2.domain > "%DOMAIN%\RegisterSaleUseCase.kt"
echo import com.seuapp.vipme2.data.local.model.Sale >> "%DOMAIN%\RegisterSaleUseCase.kt"
echo import com.seuapp.vipme2.data.local.dao.SaleDao >> "%DOMAIN%\RegisterSaleUseCase.kt"
echo import javax.inject.Inject >> "%DOMAIN%\RegisterSaleUseCase.kt"
echo class RegisterSaleUseCase @Inject constructor(private val saleDao: SaleDao) { >> "%DOMAIN%\RegisterSaleUseCase.kt"
echo     suspend operator fun invoke(sale: Sale): Long = saleDao.upsertSale(sale) >> "%DOMAIN%\RegisterSaleUseCase.kt"
echo } >> "%DOMAIN%\RegisterSaleUseCase.kt"

echo package com.seuapp.vipme2.domain > "%DOMAIN%\GetCustomerListUseCase.kt"
echo import com.seuapp.vipme2.data.local.dao.CustomerDao >> "%DOMAIN%\GetCustomerListUseCase.kt"
echo import com.seuapp.vipme2.data.local.model.Customer >> "%DOMAIN%\GetCustomerListUseCase.kt"
echo import kotlinx.coroutines.flow.Flow >> "%DOMAIN%\GetCustomerListUseCase.kt"
echo import javax.inject.Inject >> "%DOMAIN%\GetCustomerListUseCase.kt"
echo class GetCustomerListUseCase @Inject constructor(private val customerDao: CustomerDao) { >> "%DOMAIN%\GetCustomerListUseCase.kt"
echo     operator fun invoke(): Flow<List<Customer>> = customerDao.getAllCustomers() >> "%DOMAIN%\GetCustomerListUseCase.kt"
echo } >> "%DOMAIN%\GetCustomerListUseCase.kt"

echo ✅ Use Cases criados!

REM 🔹 JSON simulado
SET ASSETS=%ROOT%\..\..\..\assets

echo [ > "%ASSETS%\products.json"
echo   { >> "%ASSETS%\products.json"
echo     "id": 1, >> "%ASSETS%\products.json"
echo     "name": "Shampoo VIP", >> "%ASSETS%\products.json"
echo     "description": "Limpeza profunda e brilho", >> "%ASSETS%\products.json"
echo     "price": 19.99, >> "%ASSETS%\products.json"
echo     "imageUrl": "https://via.placeholder.com/150" >> "%ASSETS%\products.json"
echo   } >> "%ASSETS%\products.json"
echo ] >> "%ASSETS%\products.json"

echo ✅ JSON simulado criado!

REM 🔹 Navegação em MainActivity.kt
SET MAIN=%ROOT%\MainActivity.kt

echo package com.seuapp.vipme2 > "%MAIN%"
echo. >> "%MAIN%"
echo import android.os.Bundle >> "%MAIN%"
echo import androidx.activity.ComponentActivity >> "%MAIN%"
echo import androidx.activity.compose.setContent >> "%MAIN%"
echo import androidx.compose.material3.* >> "%MAIN%"
echo import androidx.compose.runtime.Composable >> "%MAIN%"
echo import androidx.navigation.compose.NavHost >> "%MAIN%"
echo import androidx.navigation.compose.composable >> "%MAIN%"
echo import androidx.navigation.compose.rememberNavController >> "%MAIN%"
echo import com.seuapp.vipme2.presentation.products.ProductScreen >> "%MAIN%"
echo import com.seuapp.vipme2.presentation.customers.CustomerScreen >> "%MAIN%"
echo import com.seuapp.vipme2.presentation.sales.SaleScreen >> "%MAIN%"
echo import com.seuapp.vipme2.presentation.cash.CashEntryScreen >> "%MAIN%"
echo import com.seuapp.vipme2.presentation.stock.StockMovementScreen >> "%MAIN%"
echo import dagger.hilt.android.AndroidEntryPoint >> "%MAIN%"
echo. >> "%MAIN%"
echo @AndroidEntryPoint >> "%MAIN%"
echo class MainActivity : ComponentActivity() { >> "%MAIN%"
echo     override fun onCreate(savedInstanceState: Bundle?) { >> "%MAIN%"
echo         super.onCreate(savedInstanceState) >> "%MAIN%"
echo         setContent { VipmeApp() } >> "%MAIN%"
echo     } >> "%MAIN%"
echo } >> "%MAIN%"
echo. >> "%MAIN%"
echo @Composable >> "%MAIN%"
echo fun VipmeApp() { >> "%MAIN%"
echo     val navController = rememberNavController() >> "%MAIN%"
echo     NavHost(navController = navController, startDestination = "products") { >> "%MAIN%"
echo         composable("products") { ProductScreen() } >> "%MAIN%"
echo         composable("customers") { CustomerScreen() } >> "%MAIN%"
echo         composable("sales") { SaleScreen() } >> "%MAIN%"
echo         composable("cash") { CashEntryScreen() } >> "%MAIN%"
echo         composable("stock") { StockMovementScreen() } >> "%MAIN%"
echo     } >> "%MAIN%"
echo } >> "%MAIN%"

echo ✅ Navegação adicionada ao MainActivity!

echo 🏁 Setup completo do projeto VIPme2 com navegação!
pause