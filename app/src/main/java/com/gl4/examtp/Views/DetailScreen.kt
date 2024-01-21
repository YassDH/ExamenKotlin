package com.gl4.examtp.Views

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gl4.examtp.Favourites.FavouritesManager
import com.gl4.examtp.R
import com.gl4.examtp.ViewModels.MovieDetailsViewModel
import com.gl4.examtp.ViewModels.MovieDetailsViewModelFactory

@SuppressLint("UnrememberedMutableState")
@Composable()
fun DetailScreen(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val movieId = navBackStackEntry?.arguments?.getString("movieId")

    val movieDetails: MovieDetailsViewModel = viewModel(factory = MovieDetailsViewModelFactory())
    movieDetails.getDetails(movieId.toString())
    val movieState = movieDetails.movieDetails.observeAsState()
    val scrollState = rememberScrollState()
    val context = LocalContext.current


    val defaultExistValue =
        movieId?.let { FavouritesManager.movieExists(movieId = it, context = context) }
    var exists: Boolean by remember { mutableStateOf(false) }
    LaunchedEffect(defaultExistValue) {
        if (defaultExistValue != null)
            exists = defaultExistValue
    }

    CompositionLocalProvider(LocalContext provides context) {

        val launchBrowser = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // Handle result if needed
        }
        Scaffold(
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                Button(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                    ),
                    shape = RoundedCornerShape(32.dp),
                    onClick = {
                        movieState.value?.let { movie ->
                            if (exists) {
                                FavouritesManager.removeFavorite(movie, context)
                            } else {
                                FavouritesManager.addFavorite(movie, context)
                            }
                        }
                        exists = !exists
                        println(exists)
                    },
                ) {
                    Text(text = if (exists) "Remove From Favorites" else "Add To Favorites")
                }
            }

        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = 16.dp, vertical = 8.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back Button")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Movie Detail")
                }
                movieState.value?.let { movie ->
                    Row(
                        modifier = Modifier
                            .height(320.dp)
                            .padding(horizontal = 24.dp)
                    ) {
                        Clickable(
                            onClick = {
                                navController.navigate(movie.imdb_link)
                            }
                        ) {
                            loadImage(
                                path = movie.big_image,
                                contentDescription = "Movie Image",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .weight(0.7f)
                                    .height(320.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                imdbLink = movie.imdb_link,
                            )
                        }
                        Spacer(modifier = Modifier.width(24.dp))
                        Column(
                            modifier = Modifier
                                .height(320.dp)
                                .weight(0.3f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            MovieInfo(
                                painterResourceId = R.drawable.baseline_videocam,
                                title = "Released",
                                value = movie.year.toString()
                            )
                            MovieInfo(
                                painterResourceId = R.drawable.baseline_leaderboard,
                                title = "Rank",
                                value = movie.rank.toString()
                            )
                            MovieInfo(
                                painterResourceId = R.drawable.baseline_stars,
                                title = "Rating",
                                value = movie.rating
                            )
                        }
                    }
                    Text(
                        movie.title,
                        modifier = Modifier.padding(
                            horizontal = 24.dp, vertical = 16.dp
                        )
                    )
                    Text(
                        "Genre",
                        modifier = Modifier.padding(
                            horizontal = 24.dp, vertical = 16.dp
                        )
                    )
                    Categories(movie.genre)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "Description", style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(
                            horizontal = 24.dp
                        )
                    )
                    Text(
                        movie.description, style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(
                            horizontal = 24.dp, vertical = 16.dp
                        )
                    )
                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
        }
    }
}

@Composable
fun Clickable(
    onClick: () -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    val clickableModifier = Modifier.clickable { onClick() }
    content(clickableModifier)
}


@Composable
fun MovieInfo(
    @DrawableRes painterResourceId: Int,
    title: String,
    value: String,
) {

    Column(
        modifier = Modifier
            .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable { }
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = painterResourceId),
            contentDescription = title,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, style = MaterialTheme.typography.titleSmall)

    }
}

@Composable
fun Categories(categories: List<String>) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier.horizontalScroll(scrollState)
    ) {
        repeat(categories.size) { index ->
            Surface(
                /// order matters
                modifier = Modifier
                    .padding(
                        start = if (index == 0) 24.dp else 0.dp,
                        end = 12.dp,
                    )
                    .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { }
                    .padding(12.dp)
            ) {
                Text(text = categories[index], style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun loadImage(
    path: String,
    contentDescription: String,
    contentScale: ContentScale = ContentScale.FillBounds,
    modifier: Modifier = Modifier,
    imdbLink: String,
) {
    val context = LocalContext.current

    GlideImage(
        model = path,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier.clickable {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(imdbLink))
            context.startActivity(browserIntent)
        }
    )
}