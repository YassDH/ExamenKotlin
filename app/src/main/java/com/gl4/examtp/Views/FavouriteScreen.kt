package com.gl4.examtp.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gl4.examtp.Favourites.FavouritesManager
import com.gl4.examtp.Models.MovieDetails.MovieDetailsResponse
import com.gl4.examtp.Models.Top100.Top100ResponseItem

@Composable()
fun FavouriteScreen(navController: NavController) {

    val favouriteList = FavouritesManager.getFavorites(LocalContext.current)
    Column{
        Text(
            text = "Favourites List",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.padding(10.dp)
        )
        // Access the movies list from the ViewModel

        if(favouriteList.isEmpty()){
            Text(
                text = "You have zero movies in your favourite list !",
                textAlign = TextAlign.Center,
                // on below line adding text color.
                color = Color.Red,
                // on below line adding font weight.
                fontWeight = FontWeight.Bold,
                // on below line adding padding from all sides.
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            favouriteList.let { movies ->
                items(movies){ movie ->
                    FavouriteCard(
                        movie=movie,
                        onMovieClick = { movieId ->
                            navController.navigate("detail/${movieId}")
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable()
fun FavouriteCard(
    movie : MovieDetailsResponse,
    onMovieClick: (String) -> Unit
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .width(200.dp)
                .height(270.dp),
            // shape = CutCornerShape(20.dp)
            elevation = CardDefaults.cardElevation(10.dp),
            //border = BorderStroke(3.dp,Color.Gray)
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick={
                onMovieClick(movie.id)
            }
        ) {
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
}