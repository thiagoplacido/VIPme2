package com.seuapp.vipme2.domain 
import com.seuapp.vipme2.data.local.model.Product 
import com.seuapp.vipme2.data.remote.RemoteRepository 
import javax.inject.Inject 
class GetProductsUseCase @Inject constructor(private val remoteRepository: RemoteRepository) { 
} 
