package com.seuapp.vipme2 
import com.seuapp.vipme2.data.remote.RemoteRepository 
import com.seuapp.vipme2.domain.GetProductsUseCase 
import io.mockk.coEvery 
import io.mockk.mockk 
import kotlinx.coroutines.test.runTest 
import org.junit.Assert.assertEquals 
import org.junit.Test 
class GetProductsUseCaseTest { 
    private val useCase = GetProductsUseCase(repository) 
    @Test 
    fun `should return products from repository`() = runTest { 
        coEvery { repository.fetchProducts() } returns emptyList() 
        val result = useCase() 
    } 
} 
