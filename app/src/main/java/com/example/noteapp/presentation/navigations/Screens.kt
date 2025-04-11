package com.example.noteapp.presentation.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(
    val route: String,
    val title: String,
    val showInBottomBar: Boolean = false,
    val showInDrawer: Boolean = false,
    val icon: ImageVector
) {
    object HomeScreen : Screens("home", "Home", true, true, Icons.Default.Home)
    object SettingScreen : Screens("setting", "Profile", true, true, Icons.Default.Person)
    object AddNotesScreen : Screens("addNotes", "Add Notes", true, true, Icons.Default.Edit)
    object NewsScreen : Screens("newsDashBoard", "News", true, true, Icons.Filled.DateRange)

    companion object {
        val bottomBarScreens = listOf(HomeScreen, AddNotesScreen, NewsScreen, SettingScreen)
        val drawerScreens = listOf(HomeScreen, AddNotesScreen, NewsScreen, SettingScreen)
    }

}