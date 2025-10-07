package com.seuapp.vipme2.data.local

import androidx.room.Room
import com.seuapp.vipme2.data.local.dao.SaleDao
import com.seuapp.vipme2.data.local.model.Product
import com.seuapp.vipme2.data.local.model.Sale
import com.seuapp.vipme2.data.local.model.SaleProductCrossRef
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.Date

class AppDatabaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var saleDao: SaleDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        saleDao = db.saleDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertSaleWithProducts_andRetrieve() = runBlocking {
        val product =
            Product(name = "Produto Teste", description = "Desc", price = 10.0, imageUrl = null)
        db.productDao().upsert(product)

        val sale = Sale(customerId = null, saleDate = Date(), totalAmount = 10.0)
        val saleId = saleDao.upsertSale(sale)

        val crossRef = SaleProductCrossRef(saleId = saleId, productId = 1)
        saleDao.insertSaleProductCrossRef(crossRef)

        val result = saleDao.getSaleWithProducts(saleId).first()
        assertEquals(1, result?.products?.size)
        assertEquals("Produto Teste", result?.products?.first()?.name)
    }
}