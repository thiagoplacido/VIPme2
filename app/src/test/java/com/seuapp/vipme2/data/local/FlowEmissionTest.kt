package com.seuapp.vipme2.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.seuapp.vipme2.data.local.model.Customer
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import java.util.*

class FlowEmissionTest {

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
    fun customerDao_emitsFlowCorrectly() = runBlocking {
        val customer = Customer(name = "Maria", phone = "123456789", email = "maria@email.com", notes = "VIP")
        db.customerDao().upsert(customer)

        val result = db.customerDao().getAllCustomers().first()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("Maria", result[0].name)
    }
}
