package com.seuapp.vipme2.presentation.products 
 
import androidx.lifecycle.ViewModel 
import androidx.lifecycle.viewModelScope 
import com.seuapp.vipme2.data.local.model.Product 
import com.seuapp.vipme2.domain.GetProductsUseCase 
import dagger.hilt.android.lifecycle.HiltViewModel 
import kotlinx.coroutines.flow.MutableStateFlow 
import kotlinx.coroutines.flow.StateFlow 
import kotlinx.coroutines.launch 
import javax.inject.Inject 
 
@HiltViewModel 
class ProductViewModel @Inject constructor( 
    private val getProductsUseCase: GetProductsUseCase 
) : ViewModel() { 
 
    init { 
        viewModelScope.launch { 
            _products.value = getProductsUseCase() 
        } 
    } 
} 
