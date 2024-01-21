package com.gl4.examtp.ViewModels

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gl4.examtp.API.RetrofitHelper
import com.gl4.examtp.Models.Top100.Top100Response
import com.gl4.examtp.Models.Top100.Top100ResponseItem

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Top100ViewModel() : ViewModel() {

    private val _top100Response = MutableLiveData<Top100Response>()
    val top100List: LiveData<Top100Response> = _top100Response

    private val _apiError = MutableLiveData<String?>(null)
    val apiError: LiveData<String?> = _apiError

    private val searchResultsMutable = MutableLiveData<List<Top100ResponseItem>>()
    var searchResults: LiveData<List<Top100ResponseItem>> = searchResultsMutable

    init {
        getData()
    }



    private fun getData() {

        RetrofitHelper.retrofitService.getTop100().enqueue(
            object : Callback<Top100Response> {
                override fun onResponse(
                    call: Call<Top100Response>,
                    response: Response<Top100Response>
                ) {
                    if (response.isSuccessful) {
                        _top100Response.value = response.body()
                        _apiError.value=null
                    } else {
                        _apiError.value = "Failed to fetch data. Please try again."
                    }
                }

                override fun onFailure(call: Call<Top100Response>, t: Throwable) {
                    _apiError.value = "Failed to fetch data. Please try again."
                }
            }
        )
    }



    fun searchMovie(movieName: String) {
        val filteredMovies = _top100Response.value?.filter {
            it.title.contains(movieName.trim(), ignoreCase = true)
        } ?: emptyList()
        if (movieName.trim() == "") {
            searchResultsMutable.value = emptyList()
        } else {
            searchResultsMutable.value = filteredMovies
        }

    }
}