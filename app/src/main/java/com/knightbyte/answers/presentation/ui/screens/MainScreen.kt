package com.knightbyte.answers.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.knightbyte.answers.presentation.components.BottomNavGraph
import com.knightbyte.answers.presentation.components.BottomNavigationBar
import com.knightbyte.answers.presentation.ui.theme.MyPurple200
import com.knightbyte.answers.presentation.ui.theme.MyPurple500
import com.knightbyte.answers.presentation.ui.theme.MyPurple700
import com.knightbyte.answers.presentation.ui.theme.promptSans
import com.knightbyte.answers.presentation.viewmodel.AnswersViewModel
import com.knightbyte.answers.utils.CredentialProvider
import com.knightbyte.answers.utils.Resource

@Composable
fun MainScreen(
    answersViewModel: AnswersViewModel = hiltViewModel()
) {
    // navigation Controller
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { CustomBottomNavigation(navController = navController) }
    ) {

        when (CredentialProvider.firebaseStatus.value) {
            is Resource.Error -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Text(
                            text = "${answersViewModel.firebaseStatus.value.message.toString()} ",
                            fontSize = 20.sp,
                            color = Color.Black

                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            modifier = Modifier
                                .background(MyPurple500)
                                .padding(10.dp)
                                .clickable {
                                    CredentialProvider.getCredentials()
                                },
                            text = "Try Again",
                            fontSize = 20.sp,
                            color = Color.Black

                        )
                    }

                }
            }
            is Resource.Loading -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is Resource.Success -> {
                answersViewModel.loadFiles()
                BottomNavGraph(
                    navController = navController,
                    answersViewModel = answersViewModel
                )
            }
            else -> {
                CredentialProvider.getCredentials()
            }
        }


    }

}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun CustomBottomNavigation(
    navController: NavHostController
) {

    val screens = listOf(
        BottomNavigationBar.Home,
        BottomNavigationBar.Search,
        BottomNavigationBar.Download
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Row(
        modifier = Modifier
            .background(MyPurple200)
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        screens.forEach { item ->
            if (currentDestination != null) {
                CustomBottomNavigationItem(
                    screen = item,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }

        }

    }

}

@ExperimentalAnimationApi
@Composable
fun CustomBottomNavigationItem(
    screen: BottomNavigationBar,
    currentDestination: NavDestination,
    navController: NavHostController
) {
    val isSelected = currentDestination.hierarchy.any { it.route == screen.route }

    val background = if (isSelected) MyPurple500 else Color.Transparent
    val contentColor = if (isSelected) MyPurple700 else Color.Black
    val onClick = {
        if (!currentDestination.hierarchy.any {
                it.route == screen.route
            }) navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Icon(
                painterResource(screen.icon),
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp),
                contentDescription = null,
                tint = contentColor
            )
            val textStyle = TextStyle(
                color = Color.Black,
                fontFamily = promptSans,
                fontWeight = FontWeight.Normal
            )
            AnimatedVisibility(visible = isSelected) {
                Text(
                    text = screen.title,
                    color = contentColor,
                    style = textStyle
                )
            }

        }
    }
}
