package com.gl4.examtp.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NetWorkErrorViewModelFactory(private val context:Context) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NetWorkErrorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NetWorkErrorViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}