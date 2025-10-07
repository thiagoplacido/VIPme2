package com.seuapp.vipme2.data.local.model

import androidx.room.Entity

@Entity(primaryKeys = ["saleId", "productId"])
data class SaleProductCrossRef(
    val saleId: Long,
    val productId: Long
)
