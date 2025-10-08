@file:Suppress("unused") 
package com.seuapp.vipme2.data.local 
 
import androidx.room.* 
import com.seuapp.vipme2.data.local.model.* 
 
@Database( 
    entities = [Product::class, Customer::class, Sale::class, SaleProductCrossRef::class, StockMovement::class, CashEntry::class], 
    version = 1, exportSchema = false 
) 
@TypeConverters(Converters::class) 
abstract class AppDatabase : RoomDatabase() { 
    abstract fun productDao(): ProductDao 
    abstract fun customerDao(): CustomerDao 
    abstract fun saleDao(): SaleDao 
    abstract fun stockMovementDao(): StockMovementDao 
    abstract fun cashEntryDao(): CashEntryDao 
} 
