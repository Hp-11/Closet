package com.hpyk.closet.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hpyk.closet.presentation.navigation.Screen
import com.hpyk.closet.ui.theme.BoutiqueBlush
import com.hpyk.closet.ui.theme.Burgundy

@Composable
fun BottomNavigation(
    currentScreen: String,
    onNavigate: (String) -> Unit
) {

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(Burgundy)
                .padding(vertical = 6.dp),

        horizontalArrangement =
            Arrangement.SpaceEvenly
    ) {

        val screens = listOf(
            Screen.Home,
            Screen.Upload,
            Screen.Wardrobe,
            Screen.Recommend
        )

        screens.forEach { screen ->

            val selected =
                currentScreen == screen.route

            IconButton(
                onClick = {
                    onNavigate(screen.route)
                }
            ) {

                Icon(
                    imageVector =
                        when (screen) {

                            Screen.Home ->
                                Icons.Filled.Home

                            Screen.Upload ->
                                Icons.Filled.AddAPhoto

                            Screen.Wardrobe ->
                                Icons.Filled.Checkroom

                            Screen.Recommend ->
                                Icons.Filled.AutoAwesome

                            Screen.Style ->
                                Icons.Filled.AutoAwesome
                        },

                    contentDescription =
                        screen.label,

                    tint =
                        if (selected)
                            BoutiqueBlush
                        else
                            MaterialTheme
                                .colorScheme
                                .onPrimary,

                    modifier =
                        Modifier.size(
                            if (selected)
                                29.dp
                            else
                                25.dp
                        )
                )
            }
        }
    }
}