package com.example.noteapp.presentation.navigations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    onItemClick: (Screens) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Surface(
        modifier = Modifier
            .width(screenWidth * 0.6f)
            .fillMaxHeight(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(top = 50.dp , bottom = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Column {
                    Text(
                        text = "John Doe",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "john@example.com",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            // 🔹 Menu Items
            Screens.drawerScreens.forEach { screen ->
                NavigationDrawerItem(
                    label = { Text(screen.title) },
                    selected = false,
                    onClick = { onItemClick(screen) },
                    icon = {
                        Icon(screen.icon, contentDescription = screen.title)
                    },
                    modifier = Modifier.padding(start = 0.dp, end= 0.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))


            Divider()


            Text(
                text = "App v1.0.0",
                modifier = Modifier
                    .padding(bottom = 50.dp ,  top = 25.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}



