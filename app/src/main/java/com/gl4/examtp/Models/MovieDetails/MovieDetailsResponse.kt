package com.gl4.examtp.Models.MovieDetails

data class MovieDetailsResponse(
    val big_image: String,
    val description: String,
    val director: List<String>,
    val genre: List<String>,
    val id: String,
    val image: String,
    val imdb_link: String,
    val imdbid: String,
    val rank: Int,
    val rating: String,
    val thumbnail: String,
    val title: String,
    val trailer: String,
    val trailer_embed_link: String,
    val trailer_youtube_id: String,
    val writers: List<String>,
    val year: Int
)