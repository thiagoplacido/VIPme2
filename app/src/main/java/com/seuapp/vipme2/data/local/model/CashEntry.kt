package com.seuapp.vipme2.data.local.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "cash_entries",
    indices = [Index(value = ["entryDate"])]
)
data class CashEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val amount: Double,
    val entryDate: Date,
    val description: String,
    val type: String // "income" or "expense"
)
