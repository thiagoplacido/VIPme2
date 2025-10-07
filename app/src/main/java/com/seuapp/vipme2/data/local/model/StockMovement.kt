package com.seuapp.vipme2.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "stock_movements")
data class StockMovement(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: Long,
    val quantityChange: Int,
    val movementDate: Date,
    val reason: String // e.g., "initial_stock", "sale", "return", "adjustment"
)
