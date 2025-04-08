package com.example.noteapp.presentation.navigations

import BottomNavigationBar
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    //for managing navigation
    val navController = rememberNavController()

    // for managing drawer by default close
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    //coroutine scope to launch coroutines
    val scope = rememberCoroutineScope()

    // navBackStackEntry for tracking navigation
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    //for current route or screen
    val currentRoute = navBackStackEntry?.destination?.route
    val currentScreen =
        Screens.drawerScreens.find { it.route == currentRoute } ?: Screens.HomeScreen
    val snackBarHostState = remember { SnackbarHostState() }


    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        DrawerContent { it ->
            navController.navigate(it.route) {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
            scope.launch {
                drawerState.close()
            }
        }
    }) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = currentScreen.title) },
                    navigationIcon = {
                        if (currentRoute == Screens.HomeScreen.route) {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        } else {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                            }
                        }

                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier.shadow(elevation = 2.dp)
                )
            },
            bottomBar = {
                if (currentRoute in Screens.bottomBarScreens.map { it.route }) {
                    BottomNavigationBar(navController, currentRoute)
                }
            },
            floatingActionButton = {
                if (currentRoute == Screens.HomeScreen.route) {
                    AddNotesButton {
                        navController.navigate(Screens.AddNotesScreen.route)
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            snackbarHost = { snackBarHostState }
        ) { innerPadding ->
            AppNavGraph(navController, Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun AddNotesButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Note"
        )
    }
}

