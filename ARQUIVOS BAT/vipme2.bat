@echo off
SET PROJ=C:\Users\Thiago\AndroidStudioProjects\VIPme2
SET APP=%PROJ%\app\build.gradle
SET SRC=%PROJ%\app\src\main\java\com\seuapp\vipme2

echo 🔧 Aplicando ajustes gerais no projeto VIPme2...

REM 🔹 Atualizar repositórios no build.gradle raiz
echo plugins { id 'com.android.application' } > "%PROJ%\build.gradle"
echo repositories { >> "%PROJ%\build.gradle"
echo     google() >> "%PROJ%\build.gradle"
echo     mavenCentral() >> "%PROJ%\build.gradle"
echo     maven { url 'https://jitpack.io' } >> "%PROJ%\build.gradle"
echo } >> "%PROJ%\build.gradle"

REM 🔹 Atualizar dependências no build.gradle do app
echo android { compileSdkVersion 34 } > "%APP%"
echo dependencies { >> "%APP%"
echo     implementation 'androidx.compose.material3:material3:1.2.0' >> "%APP%"
echo     implementation 'com.github.bumptech.glide:glide:4.15.1' >> "%APP%"
echo } >> "%APP%"

REM 🔹 Corrigir ApiService.kt
echo package com.seuapp.vipme2.data.remote > "%SRC%\data\remote\ApiService.kt"
echo import retrofit2.http.GET >> "%SRC%\data\remote\ApiService.kt"
echo import com.seuapp.vipme2.data.local.model.Product >> "%SRC%\data\remote\ApiService.kt"
echo interface ApiService { >> "%SRC%\data\remote\ApiService.kt"
echo     @GET("products") >> "%SRC%\data\remote\ApiService.kt"
echo     suspend fun getProducts(): List<Product> >> "%SRC%\data\remote\ApiService.kt"
echo } >> "%SRC%\data\remote\ApiService.kt"

REM 🔹 Corrigir MainActivity.kt para usar NavigationGraph
echo package com.seuapp.vipme2 > "%SRC%\MainActivity.kt"
echo import android.os.Bundle >> "%SRC%\MainActivity.kt"
echo import androidx.activity.ComponentActivity >> "%SRC%\MainActivity.kt"
echo import androidx.activity.compose.setContent >> "%SRC%\MainActivity.kt"
echo import androidx.navigation.compose.rememberNavController >> "%SRC%\MainActivity.kt"
echo import com.seuapp.vipme2.presentation.NavigationGraph >> "%SRC%\MainActivity.kt"
echo import dagger.hilt.android.AndroidEntryPoint >> "%SRC%\MainActivity.kt"
echo @AndroidEntryPoint >> "%SRC%\MainActivity.kt"
echo class MainActivity : ComponentActivity() { >> "%SRC%\MainActivity.kt"
echo     override fun onCreate(savedInstanceState: Bundle?) { >> "%SRC%\MainActivity.kt"
echo         super.onCreate(savedInstanceState) >> "%SRC%\MainActivity.kt"
echo         setContent { >> "%SRC%\MainActivity.kt"
echo             val navController = rememberNavController() >> "%SRC%\MainActivity.kt"
echo             NavigationGraph(navController) >> "%SRC%\MainActivity.kt"
echo         } >> "%SRC%\MainActivity.kt"
echo     } >> "%SRC%\MainActivity.kt"
echo } >> "%SRC%\MainActivity.kt"

REM 🔹 Remover MyApp.kt duplicado
del "%SRC%\MyApp.kt"

REM 🔹 Limpar e recompilar projeto
cd %PROJ%
gradlew clean build --refresh-dependencies

echo ✅ Ajustes aplicados e projeto recompilado com sucesso!
pause