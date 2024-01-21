package com.gl4.examtp.Views

import ApiErrorScreen
import NetworkErrorScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gl4.examtp.ViewModels.NetWorkErrorViewModel
import com.gl4.examtp.ViewModels.NetWorkErrorViewModelFactory
import com.gl4.examtp.ViewModels.Top100ViewModel
import com.gl4.examtp.ViewModels.Top100ViewModelFactory

@Composable()
fun Search(
    navController: NavController,
) {
    val context = LocalContext.current
    val top100ViewModel: Top100ViewModel = viewModel(factory = Top100ViewModelFactory())
    var movieName by remember { mutableStateOf("") }
    val searchResults by top100ViewModel.searchResults.observeAsState()

    val apiErrorState = top100ViewModel.apiError.observeAsState()

    val netWorkErrorViewModel: NetWorkErrorViewModel =
        viewModel(factory = NetWorkErrorViewModelFactory(context))
    val connectionErrorState = netWorkErrorViewModel.connectionError.observeAsState()

    Column {
        if (connectionErrorState.value == true) {
            NetworkErrorScreen(
                onRetry = { navController.navigate("search") },
                modifier = Modifier.fillMaxSize()
            )
        } else if (apiErrorState.value != null) {
            // Display error message or error screen
            ApiErrorScreen(
                errorMessage = apiErrorState.value!!,
                onRetry = { navController.navigate("search") },
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Text(
                text = "Search Movies",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(10.dp)
            )
            // Input field for movie name
            OutlinedTextField(
                value = movieName,
                onValueChange = { newMovieName ->
                    movieName = newMovieName
                    top100ViewModel.searchMovie(movieName)
                },
                label = { Text("Enter Movie Name") },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            // Access the movies list from the ViewModel
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                searchResults?.let { movies ->
                    items(movies) { movie ->
                        MovieCard(
                            movie = movie,
                            onMovieClick = { movieId ->
                                navController.navigate("detail/${movieId}")
                            }
                        )
                    }
                }
            }
        }
    }
}

