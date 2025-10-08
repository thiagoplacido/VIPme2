package com.seuapp.vipme2 
import app.cash.turbine.test 
import com.seuapp.vipme2.domain.GetProductsUseCase 
import com.seuapp.vipme2.presentation.products.ProductViewModel 
import io.mockk.coEvery 
import io.mockk.mockk 
import kotlinx.coroutines.test.runTest 
import org.junit.Assert.assertEquals 
import org.junit.Test 
class ProductViewModelTest { 
    private val viewModel = ProductViewModel(useCase) 
    @Test 
    fun `should load products correctly`() = runTest { 
        coEvery { useCase() } returns listOf() 
        viewModel.products.test { 
            assertEquals(emptyList(), awaitItem()) 
            cancelAndIgnoreRemainingEvents() 
        } 
    } 
} 
