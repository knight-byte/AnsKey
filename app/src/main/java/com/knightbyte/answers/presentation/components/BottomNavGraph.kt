package com.knightbyte.answers.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.knightbyte.answers.presentation.ui.screens.DownloadScreen
import com.knightbyte.answers.presentation.ui.screens.HomeScreen
import com.knightbyte.answers.presentation.ui.screens.SearchScreen
import com.knightbyte.answers.presentation.viewmodel.AnswersViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    answersViewModel: AnswersViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationBar.Home.route
    ) {
        composable(
            route = BottomNavigationBar.Home.route
        ) {
            // Home Screen Here
            HomeScreen(
                navController = navController,
                answersViewModel = answersViewModel
            )
        }

        composable(
            route = BottomNavigationBar.Search.route
        ) {
            // Search Screen Here
            SearchScreen(
                navController = navController,
                answersViewModel = answersViewModel
            )
        }

        composable(
            route = BottomNavigationBar.Download.route
        ) {
            // Download Screen Here
            DownloadScreen(
                navController = navController,
                answersViewModel = answersViewModel
            )
        }
    }
}