package com.seuapp.vipme2.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.seuapp.vipme2.data.local.model.Product
import com.seuapp.vipme2.data.local.model.StockMovement
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import java.util.*

class StockMovementDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertStockMovements_andRetrieveByProductIdDescending() = runBlocking {
        val product = Product(name = "Produto X", description = "Teste", price = 10.0, imageUrl = null)
        db.productDao().upsert(product)

        val movement1 = StockMovement(productId = product.id, quantity = 5, movementDate = Date(100000), type = "entrada")
        val movement2 = StockMovement(productId = product.id, quantity = 3, movementDate = Date(200000), type = "saida")
        val movement3 = StockMovement(productId = product.id, quantity = 2, movementDate = Date(150000), type = "entrada")

        db.stockMovementDao().upsert(movement1)
        db.stockMovementDao().upsert(movement2)
        db.stockMovementDao().upsert(movement3)

        val result = db.stockMovementDao().getMovementsByProduct(product.id).first()

        Assert.assertEquals(3, result.size)
        Assert.assertEquals(200000, result[0].movementDate.time) // mais recente
        Assert.assertEquals(150000, result[1].movementDate.time)
        Assert.assertEquals(100000, result[2].movementDate.time) // mais antiga
    }
}
