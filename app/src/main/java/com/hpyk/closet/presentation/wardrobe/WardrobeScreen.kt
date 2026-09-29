package com.hpyk.closet.presentation.wardrobe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hpyk.closet.domain.model.WardrobeItem
import com.hpyk.closet.presentation.components.BoutiqueButton
import com.hpyk.closet.ui.theme.MutedText

@Composable
fun WardrobeScreen(
    items: List<WardrobeItem>,
    onDelete: (WardrobeItem) -> Unit,
    onUpload: () -> Unit
) {

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp)
    ) {

        Text(
            text = "Your Wardrobe",
            fontSize = 26.sp,
            fontWeight =
                androidx.compose.ui.text.font.FontWeight.Bold
        )

        Text(
            text =
                "${items.size} saved item" +
                        if (items.size == 1)
                            ""
                        else
                            "s",

            color = MutedText
        )

        if (items.isEmpty()) {

            Box(
                modifier =
                    Modifier
                        .fillMaxSize(),

                contentAlignment =
                    Alignment.Center
            ) {

                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text =
                            "Your wardrobe is empty.",
                        color = MutedText
                    )

                    BoutiqueButton(
                        label =
                            "UPLOAD YOUR FIRST ITEM",
                        action = onUpload,

                        modifier =
                            Modifier
                                .padding(
                                    top = 12.dp
                                )
                                .fillMaxWidth(
                                    0.8f
                                )
                    )
                }
            }

        } else {

            LazyVerticalGrid(
                columns =
                    GridCells.Fixed(2),

                modifier =
                    Modifier.fillMaxSize(),

                horizontalArrangement =
                    Arrangement.spacedBy(12.dp),

                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items,
                    key = {
                        it.id
                    }
                ) {

                    WardrobeCard(
                        item = it,
                        onDelete = {
                            onDelete(it)
                        }
                    )
                }
            }
        }
    }
}