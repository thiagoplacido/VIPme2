package com.seuapp.vipme2.data.local.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SaleWithProducts(
    @Embedded val sale: Sale,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = SaleProductCrossRef::class,
            parentColumn = "saleId",
            entityColumn = "productId"
        )
    )
    val products: List<Product>
)
