package com.hpyk.closet.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import com.hpyk.closet.presentation.components.AppHeader
import com.hpyk.closet.presentation.components.BottomNavigation
import com.hpyk.closet.presentation.home.HomeScreen
import com.hpyk.closet.presentation.recommendation.RecommendationScreen
import com.hpyk.closet.presentation.recommendation.RecommendationViewModel
import com.hpyk.closet.presentation.style.StyleAnalysisScreen
import com.hpyk.closet.presentation.style.StyleAnalysisViewModel
import com.hpyk.closet.presentation.upload.UploadScreen
import com.hpyk.closet.presentation.upload.UploadViewModel
import com.hpyk.closet.presentation.wardrobe.WardrobeScreen
import com.hpyk.closet.presentation.wardrobe.WardrobeViewModel
import com.hpyk.closet.ui.theme.SoftSurface

@Composable
fun ClosetNavGraph(
    wardrobeViewModel: WardrobeViewModel,
    uploadViewModel: UploadViewModel,
    recommendationViewModel: RecommendationViewModel,
    onCamera: () -> Unit,
    onGallery: () -> Unit
) {
    var currentRoute by remember {
        mutableStateOf(Screen.Home.route)
    }

    val items by wardrobeViewModel
        .wardrobe
        .collectAsState()

    val styleViewModel = remember {
        StyleAnalysisViewModel()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftSurface)
    ) {
        AppHeader(
            onStyleClick = {
                currentRoute = Screen.Style.route
            }
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            when (currentRoute) {

                Screen.Home.route -> {
                    HomeScreen(
                        items = items,
                        onUpload = {
                            currentRoute = Screen.Upload.route
                        },
                        onRecommend = {
                            currentRoute = Screen.Recommend.route
                        },
                        onStyle = {
                            currentRoute = Screen.Style.route
                        }
                    )
                }

                Screen.Upload.route -> {
                    UploadScreen(
                        viewModel = uploadViewModel,
                        onCamera = onCamera,
                        onGallery = onGallery,
                        onSaved = {
                            currentRoute = Screen.Wardrobe.route
                        }
                    )
                }

                Screen.Wardrobe.route -> {
                    WardrobeScreen(
                        items = items,
                        onDelete = wardrobeViewModel::delete,
                        onUpload = {
                            currentRoute = Screen.Upload.route
                        }
                    )
                }

                Screen.Recommend.route -> {
                    RecommendationScreen(
                        items = items,
                        viewModel = recommendationViewModel
                    )
                }

                Screen.Style.route -> {
                    StyleAnalysisScreen(
                        items = items,
                        viewModel = styleViewModel
                    )
                }
            }
        }

        BottomNavigation(
            currentScreen = currentRoute,
            onNavigate = {
                currentRoute = it
            }
        )
    }
}