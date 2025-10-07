package com.seuapp.vipme2.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.seuapp.vipme2.data.local.model.StockMovement
import kotlinx.coroutines.flow.Flow

@Dao
interface StockMovementDao {
    @Query("SELECT * FROM stock_movements WHERE productId = :productId ORDER BY movementDate DESC")
    fun getStockMovementsForProduct(productId: Long): Flow<List<StockMovement>>

    @Upsert
    suspend fun upsert(stockMovement: StockMovement)
}
