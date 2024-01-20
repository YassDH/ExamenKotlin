package com.gl4.examtp.Favourites

import android.content.Context
import androidx.compose.runtime.State
import com.gl4.examtp.Models.MovieDetails.MovieDetailsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object FavouritesManager {
    private const val FAVOURITES_KEY = "favourites_key"

    private fun getSharedPreferences(context: Context) =
        context.getSharedPreferences(FAVOURITES_KEY, Context.MODE_PRIVATE)

    fun getFavorites(context: Context): List<MovieDetailsResponse> {
        val sharedPreferences = getSharedPreferences(context)
        val jsonFavorites = sharedPreferences.getString(FAVOURITES_KEY, null)

        return if (jsonFavorites != null) {
            val typeToken = object : TypeToken<List<MovieDetailsResponse>>() {}.type
            Gson().fromJson(jsonFavorites, typeToken)
        } else {
            emptyList()
        }
    }

    fun movieExists(movieId : String, context: Context): Boolean {
        val list: List<MovieDetailsResponse> = getFavorites(context)
        return list.any { it.id == movieId }
    }

    fun addFavorite(movie: MovieDetailsResponse, context: Context) {
        val sharedPreferences = getSharedPreferences(context)
        val list: List<MovieDetailsResponse> = getFavorites(context)
        val editor = sharedPreferences.edit()

        if (list.isEmpty() || !list.contains(movie)) {
            val newList = list.toMutableList()
            newList.add(movie)
            val jsonFavorites = Gson().toJson(newList)
            editor.putString(FAVOURITES_KEY, jsonFavorites)
        }

        editor.apply()
    }

    fun removeFavorite(movie: MovieDetailsResponse, context: Context) {
        val sharedPreferences = getSharedPreferences(context)
        val list: List<MovieDetailsResponse> = getFavorites(context)
        val editor = sharedPreferences.edit()

        val newList = list.toMutableList()
        val removed = newList.removeIf { it.id == movie.id }

        if (removed) {
            val jsonFavorites = Gson().toJson(newList)
            editor.putString(FAVOURITES_KEY, jsonFavorites)
        }
        editor.apply()
    }
}