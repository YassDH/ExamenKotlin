package com.gl4.examtp.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gl4.examtp.ViewModels.MovieDetailsViewModel
import com.gl4.examtp.ViewModels.MovieDetailsViewModelFactory

@Composable()
fun DetailScreen(navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val movieId = navBackStackEntry?.arguments?.getString("movieId")

    val movieDetails: MovieDetailsViewModel = viewModel(factory = MovieDetailsViewModelFactory())
    movieDetails.getDetails(movieId.toString())
    val movieState = movieDetails.movieDetails.observeAsState()

    movieState.value?.let { movie ->
        Column(modifier = Modifier.fillMaxSize()) {
            LoadImage(movie.image)
            Text(
                text = movie.title,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                modifier = Modifier.padding(10.dp),
                maxLines = 2,
            )
            Text(
                text = movie.year.toString(),
                fontSize = 13.sp,
                modifier = Modifier.padding(6.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = Color.Gray
            )
        }
    }
}