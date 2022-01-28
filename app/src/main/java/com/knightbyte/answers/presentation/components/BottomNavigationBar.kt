package com.knightbyte.answers.presentation.components

import com.knightbyte.answers.R

sealed class BottomNavigationBar(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : BottomNavigationBar(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_home_icon_svg,
    )

    object Search : BottomNavigationBar(
        route = "search",
        title = "Search",
        icon = R.drawable.ic_search_icon_svg
    )

    object Download : BottomNavigationBar(
        route = "downloads",
        title = "Downloads",
        icon = R.drawable.ic_download_icon_svg
    )
}
