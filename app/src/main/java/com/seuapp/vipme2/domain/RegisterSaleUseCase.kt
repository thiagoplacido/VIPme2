package com.seuapp.vipme2.domain 
import com.seuapp.vipme2.data.local.model.Sale 
import com.seuapp.vipme2.data.local.dao.SaleDao 
import javax.inject.Inject 
class RegisterSaleUseCase @Inject constructor(private val saleDao: SaleDao) { 
    suspend operator fun invoke(sale: Sale): Long = saleDao.upsertSale(sale) 
} 
