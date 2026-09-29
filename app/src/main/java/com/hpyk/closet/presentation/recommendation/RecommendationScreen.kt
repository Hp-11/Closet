package com.hpyk.closet.presentation.recommendation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.hpyk.closet.core.constants.ClosetConstants
import com.hpyk.closet.domain.model.WardrobeItem
import com.hpyk.closet.presentation.components.BoutiqueButton
import com.hpyk.closet.presentation.components.ChoiceField
import com.hpyk.closet.presentation.wardrobe.WardrobeCard
import com.hpyk.closet.ui.theme.Burgundy
import com.hpyk.closet.ui.theme.MutedText

@Composable
fun RecommendationScreen(
    items: List<WardrobeItem>,
    viewModel: RecommendationViewModel
) {

    val state =
        viewModel.uiState.value

    val colorOptions =
        listOf("Any color") +
                items
                    .map { it.color.trim() }
                    .filter { it.isNotEmpty() }
                    .distinct()
                    .sorted()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(20.dp)
    ) {

        Text(
            text = "Get Recommendations",
            fontSize = 26.sp,
            fontWeight =
                androidx.compose.ui.text.font.FontWeight.Bold
        )

        Text(
            text =
                "Choose the occasion, weather, mood, and a color preference. We'll curate from your own wardrobe.",

            color = MutedText
        )

        ChoiceField(
            label = "Occasion",
            selection = state.occasion,
            choices = ClosetConstants.occasions,
            onSelect = viewModel::setOccasion
        )

        ChoiceField(
            label = "Weather",
            selection = state.weather,
            choices = ClosetConstants.weatherOptions,
            onSelect = viewModel::setWeather
        )

        ChoiceField(
            label = "Mood",
            selection = state.mood,
            choices = ClosetConstants.moodOptions,
            onSelect = viewModel::setMood
        )

        ChoiceField(
            label = "Color",
            selection = state.color,
            choices = colorOptions,
            onSelect = viewModel::setColor
        )

        BoutiqueButton(
            label = "RECOMMEND FOR ME",
            action = {
                viewModel.recommend(items)
            },

            modifier =
                Modifier.padding(
                    top = 14.dp
                )
        )

        val results =
            state.recommendations

        when {

            results == null -> {

                Box(
                    modifier =
                        Modifier
                            .fillMaxSize(),

                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(
                        text =
                            "Set your preferences, then tap Recommend for me.",
                        color = MutedText
                    )
                }
            }

            results.isEmpty() -> {

                Box(
                    modifier =
                        Modifier
                            .fillMaxSize(),

                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(
                        text =
                            "No items match all of those preferences yet.",
                        color = MutedText
                    )
                }
            }

            else -> {

                Text(
                    text =
                        "Curated for ${state.weather} weather · ${state.mood} mood",

                    color = Burgundy,

                    fontWeight =
                        androidx.compose.ui.text.font.FontWeight.Bold,

                    modifier =
                        Modifier.padding(
                            top = 14.dp,
                            bottom = 10.dp
                        )
                )

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
                        results,
                        key = {
                            it.id
                        }
                    ) {

                        WardrobeCard(
                            item = it,
                            onDelete = {}
                        )
                    }
                }
            }
        }
    }
}