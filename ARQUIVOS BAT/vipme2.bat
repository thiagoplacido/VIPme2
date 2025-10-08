@echo off
SET BASE=C:\Users\Thiago\AndroidStudioProjects\VIPme2\app\src\main\java\com\seuapp\vipme2

echo Criando estrutura VIPme2...

REM Pastas principais
mkdir "%BASE%\data\local"
mkdir "%BASE%\data\remote"
mkdir "%BASE%\data\mock"
mkdir "%BASE%\di"
mkdir "%BASE%\domain"
mkdir "%BASE%\presentation\products"
mkdir "%BASE%\assets"

REM Arquivos com conteúdo real

REM AppDatabase.kt
echo @file:Suppress("unused") > "%BASE%\data\local\AppDatabase.kt"
echo package com.seuapp.vipme2.data.local >> "%BASE%\data\local\AppDatabase.kt"
echo. >> "%BASE%\data\local\AppDatabase.kt"
echo import androidx.room.* >> "%BASE%\data\local\AppDatabase.kt"
echo import com.seuapp.vipme2.data.local.model.* >> "%BASE%\data\local\AppDatabase.kt"
echo. >> "%BASE%\data\local\AppDatabase.kt"
echo @Database( >> "%BASE%\data\local\AppDatabase.kt"
echo     entities = [Product::class, Customer::class, Sale::class, SaleProductCrossRef::class, StockMovement::class, CashEntry::class], >> "%BASE%\data\local\AppDatabase.kt"
echo     version = 1, exportSchema = false >> "%BASE%\data\local\AppDatabase.kt"
echo ) >> "%BASE%\data\local\AppDatabase.kt"
echo @TypeConverters(Converters::class) >> "%BASE%\data\local\AppDatabase.kt"
echo abstract class AppDatabase : RoomDatabase() { >> "%BASE%\data\local\AppDatabase.kt"
echo     abstract fun productDao(): ProductDao >> "%BASE%\data\local\AppDatabase.kt"
echo     abstract fun customerDao(): CustomerDao >> "%BASE%\data\local\AppDatabase.kt"
echo     abstract fun saleDao(): SaleDao >> "%BASE%\data\local\AppDatabase.kt"
echo     abstract fun stockMovementDao(): StockMovementDao >> "%BASE%\data\local\AppDatabase.kt"
echo     abstract fun cashEntryDao(): CashEntryDao >> "%BASE%\data\local\AppDatabase.kt"
echo } >> "%BASE%\data\local\AppDatabase.kt"

REM ApiService.kt
echo package com.seuapp.vipme2.data.remote > "%BASE%\data\remote\ApiService.kt"
echo. >> "%BASE%\data\remote\ApiService.kt"
echo import retrofit2.http.GET >> "%BASE%\data\remote\ApiService.kt"
echo import com.seuapp.vipme2.data.local.model.Product >> "%BASE%\data\remote\ApiService.kt"
echo. >> "%BASE%\data\remote\ApiService.kt"
echo interface ApiService { >> "%BASE%\data\remote\ApiService.kt"
echo     @GET("products") >> "%BASE%\data\remote\ApiService.kt"
echo     suspend fun getProducts(): List<Product> >> "%BASE%\data\remote\ApiService.kt"
echo } >> "%BASE%\data\remote\ApiService.kt"

REM NetworkModule.kt
echo package com.seuapp.vipme2.di > "%BASE%\di\NetworkModule.kt"
echo. >> "%BASE%\di\NetworkModule.kt"
echo import com.seuapp.vipme2.data.remote.ApiService >> "%BASE%\di\NetworkModule.kt"
echo import dagger.Module >> "%BASE%\di\NetworkModule.kt"
echo import dagger.Provides >> "%BASE%\di\NetworkModule.kt"
echo import dagger.hilt.InstallIn >> "%BASE%\di\NetworkModule.kt"
echo import dagger.hilt.components.SingletonComponent >> "%BASE%\di\NetworkModule.kt"
echo import retrofit2.Retrofit >> "%BASE%\di\NetworkModule.kt"
echo import retrofit2.converter.gson.GsonConverterFactory >> "%BASE%\di\NetworkModule.kt"
echo import javax.inject.Singleton >> "%BASE%\di\NetworkModule.kt"
echo. >> "%BASE%\di\NetworkModule.kt"
echo @Module >> "%BASE%\di\NetworkModule.kt"
echo @InstallIn(SingletonComponent::class) >> "%BASE%\di\NetworkModule.kt"
echo object NetworkModule { >> "%BASE%\di\NetworkModule.kt"
echo     @Provides >> "%BASE%\di\NetworkModule.kt"
echo     fun provideBaseUrl() = "https://suaapi.com.br/" >> "%BASE%\di\NetworkModule.kt"
echo. >> "%BASE%\di\NetworkModule.kt"
echo     @Provides >> "%BASE%\di\NetworkModule.kt"
echo     @Singleton >> "%BASE%\di\NetworkModule.kt"
echo     fun provideRetrofit(baseUrl: String): Retrofit = >> "%BASE%\di\NetworkModule.kt"
echo         Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build() >> "%BASE%\di\NetworkModule.kt"
echo. >> "%BASE%\di\NetworkModule.kt"
echo     @Provides >> "%BASE%\di\NetworkModule.kt"
echo     @Singleton >> "%BASE%\di\NetworkModule.kt"
echo     fun provideApiService(retrofit: Retrofit): ApiService = >> "%BASE%\di\NetworkModule.kt"
echo         retrofit.create(ApiService::class.java) >> "%BASE%\di\NetworkModule.kt"
echo } >> "%BASE%\di\NetworkModule.kt"

REM RemoteRepository.kt
echo package com.seuapp.vipme2.data.remote > "%BASE%\data\remote\RemoteRepository.kt"
echo. >> "%BASE%\data\remote\RemoteRepository.kt"
echo import com.seuapp.vipme2.data.local.model.Product >> "%BASE%\data\remote\RemoteRepository.kt"
echo import javax.inject.Inject >> "%BASE%\data\remote\RemoteRepository.kt"
echo. >> "%BASE%\data\remote\RemoteRepository.kt"
echo class RemoteRepository @Inject constructor( >> "%BASE%\data\remote\RemoteRepository.kt"
echo     private val apiService: ApiService >> "%BASE%\data\remote\RemoteRepository.kt"
echo ) { >> "%BASE%\data\remote\RemoteRepository.kt"
echo     suspend fun fetchProducts(): List<Product> = apiService.getProducts() >> "%BASE%\data\remote\RemoteRepository.kt"
echo } >> "%BASE%\data\remote\RemoteRepository.kt"

REM products.json
echo [ > "%BASE%\..\..\..\assets\products.json"
echo   { >> "%BASE%\..\..\..\assets\products.json"
echo     "id": 1, >> "%BASE%\..\..\..\assets\products.json"
echo     "name": "Shampoo VIP", >> "%BASE%\..\..\..\assets\products.json"
echo     "description": "Limpeza profunda e brilho", >> "%BASE%\..\..\..\assets\products.json"
echo     "price": 19.99, >> "%BASE%\..\..\..\assets\products.json"
echo     "imageUrl": "https://via.placeholder.com/150" >> "%BASE%\..\..\..\assets\products.json"
echo   } >> "%BASE%\..\..\..\assets\products.json"
echo ] >> "%BASE%\..\..\..\assets\products.json"

echo Estrutura e arquivos criados com sucesso!
pause