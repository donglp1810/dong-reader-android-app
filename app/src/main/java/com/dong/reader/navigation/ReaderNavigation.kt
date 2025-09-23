package com.dong.reader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dong.reader.screens.ReaderSplashScreen
import com.dong.reader.screens.home.Home

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreen.SplashScreen.name) {
        composable(ReaderScreen.SplashScreen.name) {
            ReaderSplashScreen(navController = navController)
        }
        composable(ReaderScreen.ReaderHomeScreen.name) {
            Home(navController = navController)
        }
    }

}