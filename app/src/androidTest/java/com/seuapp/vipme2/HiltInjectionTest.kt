package com.seuapp.vipme2 
import androidx.test.ext.junit.runners.AndroidJUnit4 
import dagger.hilt.android.testing.HiltAndroidRule 
import dagger.hilt.android.testing.HiltAndroidTest 
import org.junit.Rule 
import org.junit.Test 
import org.junit.runner.RunWith 
@HiltAndroidTest 
@RunWith(AndroidJUnit4::class) 
class HiltInjectionTest { 
    @get:Rule 
    val hiltRule = HiltAndroidRule(this) 
    @Test 
    fun injectDependencies() { 
        hiltRule.inject() 
        // Verificações aqui 
    } 
} 
