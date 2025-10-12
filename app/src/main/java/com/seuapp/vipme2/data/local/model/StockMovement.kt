package com.seuapp.vipme2.data.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "stock_movements",
    indices = [
        Index(value = ["productId"]),
        Index(value = ["movementDate"])
    ]
)
data class StockMovement(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: Long,
    val quantity: Int,
    val movementDate: Date,
    val type: MovementType // "entrada" ou "saida"
)