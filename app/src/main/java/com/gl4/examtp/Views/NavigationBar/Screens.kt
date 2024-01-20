package com.gl4.examtp.Views.NavigationBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screens("home", "Home", Icons.Default.Home)
    object Detail : Screens("detail/{movieId}", "Detail", Icons.Default.Info)
    object Favourite : Screens("favourites", "Favourites", Icons.Default.Star)
}