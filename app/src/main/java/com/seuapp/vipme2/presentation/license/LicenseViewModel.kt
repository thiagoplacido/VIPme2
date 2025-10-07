package com.seuapp.vipme2.presentation.license

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seuapp.vipme2.data.billing.BillingClientWrapper
import com.seuapp.vipme2.data.repository.LicenseRepository
import com.seuapp.vipme2.data.repository.LicenseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LicenseViewModel @Inject constructor(
    private val licenseRepository: LicenseRepository,
    private val billingClient: BillingClientWrapper
) : ViewModel() {

    val licenseStatus: StateFlow<LicenseStatus> = licenseRepository.licenseStatus
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = LicenseStatus.Unknown
        )

    init {
        // Observa o status da compra do BillingClient e ativa o premium se a compra for bem-sucedida
        viewModelScope.launch {
            billingClient.purchaseStatus.collectLatest { hasPurchased ->
                if (hasPurchased) {
                    licenseRepository.activatePremium()
                }
            }
        }
    }

    fun launchPurchaseFlow(activity: Activity) {
        billingClient.launchPurchaseFlow(activity)
    }

    fun restorePurchases() {
        billingClient.queryPurchases()
    }
}
