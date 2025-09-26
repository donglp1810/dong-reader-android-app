package com.dong.reader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dong.reader.screens.ReaderSplashScreen
import com.dong.reader.screens.details.BookDetailsScreen
import com.dong.reader.screens.home.Home
import com.dong.reader.screens.login.ReaderLoginScreen
import com.dong.reader.screens.search.BookSearchScreen
import com.dong.reader.screens.stats.ReaderStatsScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name) {
        composable(ReaderScreens.SplashScreen.name) {
            ReaderSplashScreen(navController = navController)
        }
        composable(ReaderScreens.LoginScreen.name) {
            ReaderLoginScreen(navController = navController)
        }
        composable(ReaderScreens.ReaderHomeScreen.name) {
            Home(navController = navController)
        }
        composable(ReaderScreens.ReaderStatsScreen.name) {
            ReaderStatsScreen(navController = navController)
        }
        composable(ReaderScreens.SearchScreen.name) {
            BookSearchScreen(navController = navController)
        }
        composable(ReaderScreens.DetailScreen.name) {
            BookDetailsScreen(navController = navController)
        }
    }

}