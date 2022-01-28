package com.knightbyte.answers.presentation.ui.screens

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.knightbyte.answers.presentation.components.BottomNavGraph
import com.knightbyte.answers.presentation.components.BottomNavigationBar

@Composable
fun MainScreen() {

    // navigation Controller
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(
            navController = navController
        )
    }

}

@Composable
fun BottomBar(
    navController: NavHostController
) {
    // bottom navigation bar options
    val screens = listOf(
        BottomNavigationBar.Home,
        BottomNavigationBar.Search,
        BottomNavigationBar.Download
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            if (currentDestination != null) {
                SingleOption(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}


@Composable
fun RowScope.SingleOption(
    screen: BottomNavigationBar,
    currentDestination: NavDestination,
    navController: NavHostController
) {
    // this checks is current screen matches with bottom nav
    val isSelect = currentDestination.hierarchy.any { it.route == screen.route }

    BottomNavigationItem(
        label = {
            Text(
                text = screen.title
            )
        },
        icon = {
            Icon(
                painterResource(id = screen.icon),
                contentDescription = "",
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )
        },
        selected = isSelect,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),

        // onClick redirect to respected Screen
        onClick = {
            if (!currentDestination.hierarchy.any {
                    it.route == screen.route
                }) navController.navigate(screen.route) {
                popUpTo(BottomNavigationBar.Home.route)
            }
        }
    )
}
