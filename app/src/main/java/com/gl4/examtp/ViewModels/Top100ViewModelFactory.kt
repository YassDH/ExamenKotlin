package com.gl4.examtp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Top100ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Top100ViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return Top100ViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}