package com.seuapp.vipme2.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.seuapp.vipme2.data.local.model.CashEntry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import java.util.*

class CashEntryDaoTest {

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
    fun insertCashEntries_andRetrieveInDescendingOrder() = runBlocking {
        val entry1 = CashEntry(amount = 100.0, entryDate = Date(100000), description = "Entrada A", type = "income")
        val entry2 = CashEntry(amount = 50.0, entryDate = Date(200000), description = "Entrada B", type = "expense")
        val entry3 = CashEntry(amount = 75.0, entryDate = Date(150000), description = "Entrada C", type = "income")

        db.cashEntryDao().upsert(entry1)
        db.cashEntryDao().upsert(entry2)
        db.cashEntryDao().upsert(entry3)

        val result = db.cashEntryDao().getAllCashEntries().first()

        Assert.assertEquals(3, result.size)
        Assert.assertEquals("Entrada B", result[0].description) // mais recente
        Assert.assertEquals("Entrada C", result[1].description)
        Assert.assertEquals("Entrada A", result[2].description) // mais antiga
    }
}
