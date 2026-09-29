package com.hpyk.closet.presentation.navigation

sealed class Screen(
    val route: String,
    val label: String
) {

    data object Home :
        Screen(
            route = "home",
            label = "Home"
        )

    data object Upload :
        Screen(
            route = "upload",
            label = "Upload"
        )

    data object Wardrobe :
        Screen(
            route = "wardrobe",
            label = "Wardrobe"
        )

    data object Recommend :
        Screen(
            route = "recommend",
            label = "Recommend"
        )

    data object Style :
        Screen(
            route = "style",
            label = "Your Style"
        )
}