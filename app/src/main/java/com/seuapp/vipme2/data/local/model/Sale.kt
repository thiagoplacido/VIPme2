package com.seuapp.vipme2.data.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "sales",
    indices = [Index(value = ["saleDate"])]
)
data class Sale(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val customerId: Long?,
    val saleDate: Date,
    val totalAmount: Double
)
