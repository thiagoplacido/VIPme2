package com.seuapp.vipme2.domain 
import com.seuapp.vipme2.data.local.dao.CustomerDao 
import com.seuapp.vipme2.data.local.model.Customer 
import kotlinx.coroutines.flow.Flow 
import javax.inject.Inject 
class GetCustomerListUseCase @Inject constructor(private val customerDao: CustomerDao) { 
} 
