package com.example.noteapp.presentation.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noteapp.presentation.screens.AddNotesScreen
import com.example.noteapp.presentation.screens.HomeScreen
import com.example.noteapp.presentation.screens.SettingScreen


@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = Screens.HomeScreen.route, modifier = modifier) {
        composable(Screens.HomeScreen.route) { HomeScreen() }
        composable(Screens.AddNotesScreen.route) { AddNotesScreen() }
        composable(Screens.SettingScreen.route) { SettingScreen() }
    }
}
