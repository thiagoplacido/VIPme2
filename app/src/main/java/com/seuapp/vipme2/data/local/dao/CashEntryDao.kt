package com.seuapp.vipme2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.seuapp.vipme2.data.local.model.CashEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface CashEntryDao {
    @Query("SELECT * FROM cash_entries ORDER BY entryDate DESC")
    fun getAllCashEntries(): Flow<List<CashEntry>>

    @Insert
    suspend fun insert(cashEntry: CashEntry)
}
