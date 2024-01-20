package com.gl4.examtp.Favourites

import android.content.Context
import com.gl4.examtp.Models.MovieDetails.MovieDetailsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object FavouritesManager {
    private const val FAVOURITES_KEY = "favourites_key"

    fun getFavorites(context: Context): List<MovieDetailsResponse> {
        val sharedPreferences = context.getSharedPreferences(FAVOURITES_KEY, Context.MODE_PRIVATE)
        val jsonFavorites = sharedPreferences.getString(FAVOURITES_KEY, null)

        return if (jsonFavorites != null) {
            val typeToken = object : TypeToken<List<MovieDetailsResponse>>() {}.type
            Gson().fromJson(jsonFavorites, typeToken)
        } else {
            emptyList()
        }
    }

    fun movieExists(movie: MovieDetailsResponse, context: Context) : Boolean{
        val sharedPreferences = context.getSharedPreferences(FAVOURITES_KEY, Context.MODE_PRIVATE)
        val list : List<MovieDetailsResponse> = getFavorites(context)
        return list.contains(movie)
    }

    fun addFavorite(movie: MovieDetailsResponse, context: Context) {
        val sharedPreferences = context.getSharedPreferences(FAVOURITES_KEY, Context.MODE_PRIVATE)
        val list : List<MovieDetailsResponse> = getFavorites(context)
        val editor = sharedPreferences.edit()
        if(list.isEmpty()){
            val newList = mutableListOf<MovieDetailsResponse>()
            newList.add(movie)
            val jsonFavorites = Gson().toJson(newList)
            editor.putString(FAVOURITES_KEY, jsonFavorites)
        }else{
            if(list.contains(movie)){
                editor.apply()
                return
            }
            val newList = list.toMutableList()
            newList.add(movie)
            val jsonFavorites = Gson().toJson(newList)
            editor.putString(FAVOURITES_KEY, jsonFavorites)
        }
        editor.apply()
    }
}