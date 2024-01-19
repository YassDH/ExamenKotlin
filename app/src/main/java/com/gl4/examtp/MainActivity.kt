package com.gl4.examtp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gl4.examtp.Views.DetailScreen
import com.gl4.examtp.Views.HomeScreen
import com.gl4.examtp.ui.theme.ExamTPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamTPTheme {
                // Create a NavHostController
                val navController = rememberNavController()

                // Create a NavHost with a start destination
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        // Pass the onNavigate lambda to HomeScreen
                        HomeScreen(navController)
                    }
                    composable("detail/{movieId}") {
                        DetailScreen(navController)
                    }
                }
            }
        }
    }
}