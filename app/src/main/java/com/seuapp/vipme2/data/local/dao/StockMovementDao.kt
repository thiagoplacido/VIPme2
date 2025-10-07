package com.seuapp.vipme2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.seuapp.vipme2.data.local.model.StockMovement
import kotlinx.coroutines.flow.Flow

@Dao
interface StockMovementDao {
    @Query("SELECT * FROM stock_movements WHERE productId = :productId ORDER BY movementDate DESC")
    fun getStockMovementsForProduct(productId: Long): Flow<List<StockMovement>>

    @Insert
    suspend fun insert(stockMovement: StockMovement)
}
