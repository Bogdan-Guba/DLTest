package com.example.testappdl.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.testappdl.NavRoutes.HOME_SCREEN
import com.example.testappdl.NavRoutes.SETTING_SCREEN


sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object Home : BottomNavItem(HOME_SCREEN, Icons.Default.Home, "Home")
    object Settings : BottomNavItem(SETTING_SCREEN, Icons.Default.Settings, "Settings")


}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Settings

    )


    val navBackStackEntry = navController.currentBackStackEntryAsState().value

    val currentDestination = navBackStackEntry?.destination

    NavigationBar {

        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title,
                        modifier = Modifier.size(24.dp) 
                    )
                },
                label = { Text(screen.title) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },

            )
        }
    }
}