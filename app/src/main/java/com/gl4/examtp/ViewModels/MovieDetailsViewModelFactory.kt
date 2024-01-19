package com.gl4.examtp.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MovieDetailsViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}