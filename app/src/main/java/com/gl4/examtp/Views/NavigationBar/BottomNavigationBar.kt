package com.gl4.examtp.Views.NavigationBar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gl4.examtp.Views.DetailScreen
import com.gl4.examtp.Views.FavouriteScreen
import com.gl4.examtp.Views.HomeScreen


@Preview()
@Composable()
fun BottomNavigationBar() {
//initializing the default selected item
    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }
    /**
     * by using the rememberNavController()
     * we can get the instance of the navController
     */
    val navController = rememberNavController()

//scaffold to hold our bottom navigation Bar
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                //getting the list of bottom navigation items for our data class
                BottomNavigationItem().bottomNavigationItems().forEachIndexed {index,navigationItem ->

                    //iterating all items with their respective indexes
                    NavigationBarItem(
                        selected = index == navigationSelectedItem,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navigationSelectedItem = index
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)) {
            composable(Screens.Home.route) {
                //call our composable screens here
                HomeScreen(navController)
            }
            composable(Screens.Detail.route) {
                //call our composable screens here
                DetailScreen(navController)
            }
            composable(Screens.Favourite.route) {
                //call our composable screens here
                FavouriteScreen(navController)
            }
        }
    }
}