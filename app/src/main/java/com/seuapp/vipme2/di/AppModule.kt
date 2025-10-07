package com.seuapp.vipme2.di

import android.content.Context
import com.seuapp.vipme2.data.local.AppDatabase
import com.seuapp.vipme2.data.local.dao.* // Import all DAOs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao {
        return db.productDao()
    }

    @Provides
    fun provideCustomerDao(db: AppDatabase): CustomerDao {
        return db.customerDao()
    }

    @Provides
    fun provideSaleDao(db: AppDatabase): SaleDao {
        return db.saleDao()
    }

    @Provides
    fun provideStockMovementDao(db: AppDatabase): StockMovementDao {
        return db.stockMovementDao()
    }

    @Provides
    fun provideCashEntryDao(db: AppDatabase): CashEntryDao {
        return db.cashEntryDao()
    }
}
