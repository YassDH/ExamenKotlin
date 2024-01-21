package com.gl4.examtp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gl4.examtp.API.RetrofitHelper
import com.gl4.examtp.Models.MovieDetails.MovieDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsViewModel() : ViewModel() {
    private val movieDetailsResponse = MutableLiveData<MovieDetailsResponse>()
    var movieDetails : LiveData<MovieDetailsResponse> = movieDetailsResponse

    private val _apiError = MutableLiveData<String>("")
    val apiError: LiveData<String> = _apiError
     fun getDetails(id : String){
        RetrofitHelper.retrofitService.getMovieById(id).enqueue(
            object : Callback<MovieDetailsResponse> {
                override fun onResponse(
                    call: Call<MovieDetailsResponse>,
                    response: Response<MovieDetailsResponse>
                ) {
                    if(response.isSuccessful){
                        movieDetailsResponse.value = response.body()
                        _apiError.value=""
                    }else{
                        _apiError.value = "Failed to fetch data. Please try again."
                    }
                }
                override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                    _apiError.value = "Failed to fetch data. Please try again."
                }

            }
        )
    }
}