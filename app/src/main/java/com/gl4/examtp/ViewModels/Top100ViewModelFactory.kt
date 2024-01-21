package com.gl4.examtp.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Top100ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Top100ViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return Top100ViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}