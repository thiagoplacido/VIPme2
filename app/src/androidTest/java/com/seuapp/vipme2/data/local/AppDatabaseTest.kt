package com.seuapp.vipme2.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.seuapp.vipme2.data.local.dao.SaleDao
import com.seuapp.vipme2.data.local.model.Product
import com.seuapp.vipme2.data.local.model.Sale
import com.seuapp.vipme2.data.local.model.SaleProductCrossRef
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var saleDao: SaleDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries() // Permite queries na thread principal para testes
            .build()
        saleDao = db.saleDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertSaleWithProducts_andRetrieve() = runBlocking {
        // Given
        val product = Product(id = 1, name = "Produto Teste", description = "Desc", price = 10.0, imageUrl = null)
        db.productDao().upsert(product)

        val sale = Sale(id = 1, customerId = null, saleDate = Date(), totalAmount = 10.0)
        val saleId = saleDao.upsertSale(sale)

        val crossRef = SaleProductCrossRef(saleId = saleId, productId = 1)
        saleDao.insertSaleProductCrossRef(crossRef)

        // When
        val result = saleDao.getSaleWithProducts(saleId).first()

        // Then
        assertEquals(1, result?.products?.size)
        assertEquals("Produto Teste", result?.products?.first()?.name)
    }
}
