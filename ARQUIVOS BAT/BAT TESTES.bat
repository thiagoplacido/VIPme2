@echo off
SET TESTROOT=C:\Users\Thiago\AndroidStudioProjects\VIPme2\app\src

echo 🧪 Criando estrutura de testes...

mkdir "%TESTROOT%\test\java\com\seuapp\vipme2"
mkdir "%TESTROOT%\androidTest\java\com\seuapp\vipme2"

REM 🔹 Teste de ProductViewModel
SET UNIT=%TESTROOT%\test\java\com\seuapp\vipme2

echo package com.seuapp.vipme2 > "%UNIT%\ProductViewModelTest.kt"
echo import app.cash.turbine.test >> "%UNIT%\ProductViewModelTest.kt"
echo import com.seuapp.vipme2.domain.GetProductsUseCase >> "%UNIT%\ProductViewModelTest.kt"
echo import com.seuapp.vipme2.presentation.products.ProductViewModel >> "%UNIT%\ProductViewModelTest.kt"
echo import io.mockk.coEvery >> "%UNIT%\ProductViewModelTest.kt"
echo import io.mockk.mockk >> "%UNIT%\ProductViewModelTest.kt"
echo import kotlinx.coroutines.test.runTest >> "%UNIT%\ProductViewModelTest.kt"
echo import org.junit.Assert.assertEquals >> "%UNIT%\ProductViewModelTest.kt"
echo import org.junit.Test >> "%UNIT%\ProductViewModelTest.kt"
echo class ProductViewModelTest { >> "%UNIT%\ProductViewModelTest.kt"
echo     private val useCase = mockk<GetProductsUseCase>() >> "%UNIT%\ProductViewModelTest.kt"
echo     private val viewModel = ProductViewModel(useCase) >> "%UNIT%\ProductViewModelTest.kt"
echo     @Test >> "%UNIT%\ProductViewModelTest.kt"
echo     fun `should load products correctly`() = runTest { >> "%UNIT%\ProductViewModelTest.kt"
echo         coEvery { useCase() } returns listOf() >> "%UNIT%\ProductViewModelTest.kt"
echo         viewModel.products.test { >> "%UNIT%\ProductViewModelTest.kt"
echo             assertEquals(emptyList(), awaitItem()) >> "%UNIT%\ProductViewModelTest.kt"
echo             cancelAndIgnoreRemainingEvents() >> "%UNIT%\ProductViewModelTest.kt"
echo         } >> "%UNIT%\ProductViewModelTest.kt"
echo     } >> "%UNIT%\ProductViewModelTest.kt"
echo } >> "%UNIT%\ProductViewModelTest.kt"

REM 🔹 Teste de GetProductsUseCase
echo package com.seuapp.vipme2 > "%UNIT%\GetProductsUseCaseTest.kt"
echo import com.seuapp.vipme2.data.remote.RemoteRepository >> "%UNIT%\GetProductsUseCaseTest.kt"
echo import com.seuapp.vipme2.domain.GetProductsUseCase >> "%UNIT%\GetProductsUseCaseTest.kt"
echo import io.mockk.coEvery >> "%UNIT%\GetProductsUseCaseTest.kt"
echo import io.mockk.mockk >> "%UNIT%\GetProductsUseCaseTest.kt"
echo import kotlinx.coroutines.test.runTest >> "%UNIT%\GetProductsUseCaseTest.kt"
echo import org.junit.Assert.assertEquals >> "%UNIT%\GetProductsUseCaseTest.kt"
echo import org.junit.Test >> "%UNIT%\GetProductsUseCaseTest.kt"
echo class GetProductsUseCaseTest { >> "%UNIT%\GetProductsUseCaseTest.kt"
echo     private val repository = mockk<RemoteRepository>() >> "%UNIT%\GetProductsUseCaseTest.kt"
echo     private val useCase = GetProductsUseCase(repository) >> "%UNIT%\GetProductsUseCaseTest.kt"
echo     @Test >> "%UNIT%\GetProductsUseCaseTest.kt"
echo     fun `should return products from repository`() = runTest { >> "%UNIT%\GetProductsUseCaseTest.kt"
echo         coEvery { repository.fetchProducts() } returns emptyList() >> "%UNIT%\GetProductsUseCaseTest.kt"
echo         val result = useCase() >> "%UNIT%\GetProductsUseCaseTest.kt"
echo         assertEquals(emptyList<Product>(), result) >> "%UNIT%\GetProductsUseCaseTest.kt"
echo     } >> "%UNIT%\GetProductsUseCaseTest.kt"
echo } >> "%UNIT%\GetProductsUseCaseTest.kt"

REM 🔹 Teste instrumentado com Hilt
SET INSTR=%TESTROOT%\androidTest\java\com\seuapp\vipme2

echo package com.seuapp.vipme2 > "%INSTR%\HiltInjectionTest.kt"
echo import androidx.test.ext.junit.runners.AndroidJUnit4 >> "%INSTR%\HiltInjectionTest.kt"
echo import dagger.hilt.android.testing.HiltAndroidRule >> "%INSTR%\HiltInjectionTest.kt"
echo import dagger.hilt.android.testing.HiltAndroidTest >> "%INSTR%\HiltInjectionTest.kt"
echo import org.junit.Rule >> "%INSTR%\HiltInjectionTest.kt"
echo import org.junit.Test >> "%INSTR%\HiltInjectionTest.kt"
echo import org.junit.runner.RunWith >> "%INSTR%\HiltInjectionTest.kt"
echo @HiltAndroidTest >> "%INSTR%\HiltInjectionTest.kt"
echo @RunWith(AndroidJUnit4::class) >> "%INSTR%\HiltInjectionTest.kt"
echo class HiltInjectionTest { >> "%INSTR%\HiltInjectionTest.kt"
echo     @get:Rule >> "%INSTR%\HiltInjectionTest.kt"
echo     val hiltRule = HiltAndroidRule(this) >> "%INSTR%\HiltInjectionTest.kt"
echo     @Test >> "%INSTR%\HiltInjectionTest.kt"
echo     fun injectDependencies() { >> "%INSTR%\HiltInjectionTest.kt"
echo         hiltRule.inject() >> "%INSTR%\HiltInjectionTest.kt"
echo         // Verificações aqui >> "%INSTR%\HiltInjectionTest.kt"
echo     } >> "%INSTR%\HiltInjectionTest.kt"
echo } >> "%INSTR%\HiltInjectionTest.kt"

echo ✅ Testes automatizados criados!
pause