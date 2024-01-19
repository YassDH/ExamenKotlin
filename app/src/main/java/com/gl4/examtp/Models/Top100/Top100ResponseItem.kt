package com.gl4.examtp.Models.Top100

data class Top100ResponseItem(
    val big_image: String,
    val description: String,
    val genre: List<String>,
    val id: String,
    val image: String,
    val imdb_link: String,
    val imdbid: String,
    val rank: Int,
    val rating: String,
    val thumbnail: String,
    val title: String,
    val year: Int
)