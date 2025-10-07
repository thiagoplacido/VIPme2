package com.seuapp.vipme2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.seuapp.vipme2.data.local.model.Sale
import com.seuapp.vipme2.data.local.model.SaleProductCrossRef
import com.seuapp.vipme2.data.local.model.SaleWithProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface SaleDao {

    @Upsert
    suspend fun upsertSale(sale: Sale): Long

    @Insert
    suspend fun insertSaleProductCrossRef(crossRef: SaleProductCrossRef)

    @Transaction
    @Query("SELECT * FROM sales WHERE id = :saleId")
    fun getSaleWithProducts(saleId: Long): Flow<SaleWithProducts?>

    @Transaction
    @Query("SELECT * FROM sales ORDER BY saleDate DESC")
    fun getAllSalesWithProducts(): Flow<List<SaleWithProducts>>
}
