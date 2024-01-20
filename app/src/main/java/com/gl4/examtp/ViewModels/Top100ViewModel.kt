package com.gl4.examtp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gl4.examtp.API.RetrofitHelper
import com.gl4.examtp.Models.Top100.Top100Response
import com.gl4.examtp.Models.Top100.Top100ResponseItem

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Top100ViewModel : ViewModel() {
    private val Top100Response = MutableLiveData<Top100Response>()
    var top100List : LiveData<Top100Response> = Top100Response

    private val searchResultsMutable = MutableLiveData<List<Top100ResponseItem>>()
    var searchResults : LiveData<List<Top100ResponseItem>> = searchResultsMutable

    init {
        getData()
    }

    private fun getData(){
        RetrofitHelper.retrofitService.getTop100().enqueue(
            object : Callback<Top100Response> {
                override fun onResponse(
                    call: Call<Top100Response>,
                    response: Response<Top100Response>
                ) {
                    if(response.isSuccessful){
                        Top100Response.value = response.body()
                    }else{
                        println("Weather update failed :ge}")
                    }
                }
                override fun onFailure(call: Call<Top100Response>, t: Throwable) {
                    println("Weather update failed : ${t.message}")
                }

            }
        )
    }

    fun searchMovie(movieName: String){
        val filteredMovies = Top100Response.value?.filter {
            it.title.contains(movieName.trim(), ignoreCase = true)
        } ?: emptyList()
        if(movieName.trim() == ""){
            searchResultsMutable.value = emptyList()
        }else{
            searchResultsMutable.value = filteredMovies
        }
    }
}