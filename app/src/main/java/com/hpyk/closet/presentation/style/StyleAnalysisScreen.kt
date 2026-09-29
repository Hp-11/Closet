package com.hpyk.closet.presentation.style

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hpyk.closet.core.constants.ClosetConstants
import com.hpyk.closet.domain.model.WardrobeItem
import com.hpyk.closet.presentation.components.AnalysisCard
import com.hpyk.closet.presentation.components.MatchBar
import com.hpyk.closet.ui.theme.BoutiqueBlush
import com.hpyk.closet.ui.theme.Burgundy
import com.hpyk.closet.ui.theme.Charcoal
import com.hpyk.closet.ui.theme.MutedText

@Composable
fun StyleAnalysisScreen(
    items: List<WardrobeItem>,
    viewModel: StyleAnalysisViewModel
) {

    val total =
        items.size.coerceAtLeast(1)

    val weatherReady =
        viewModel.weatherReadyCount(items)

    val moodReady =
        viewModel.moodReadyCount(items)

    val colorCounts =
        viewModel.colorCounts(items)

    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(
                    20.dp
                )
    ) {

        item {

            Text(
                text = "Your Style Analysis",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Charcoal
            )

            Spacer(
                modifier =
                    Modifier.height(6.dp)
            )

            Text(
                text =
                    "See how your wardrobe matches the indicators used for recommendations.",
                color = MutedText
            )

            Spacer(
                modifier =
                    Modifier.height(18.dp)
            )
        }

        item {

            AnalysisCard(
                title = "Outfit Match Readiness",
                subtitle =
                    "How much of your wardrobe can respond to each indicator."
            ) {

                MatchBar(
                    label = "Occasion labels",
                    value =
                        items.count {
                            it.occasion.isNotBlank()
                        },
                    total = total,
                    color = Burgundy
                )

                MatchBar(
                    label = "Weather-ready categories",
                    value = weatherReady,
                    total = total,
                    color = Burgundy
                )

                MatchBar(
                    label = "Mood-color matches",
                    value = moodReady,
                    total = total,
                    color = BoutiqueBlush
                )

                MatchBar(
                    label = "Color-labelled outfits",
                    value =
                        items.count {
                            it.color.isNotBlank()
                        },
                    total = total,
                    color = Burgundy
                )
            }
        }

        item {

            AnalysisCard(
                title = "Occasion Coverage",
                subtitle =
                    "Each bar shows the number of items available for that occasion."
            ) {

                ClosetConstants.occasions
                    .filter {
                        viewModel.occasionCount(
                            items,
                            it
                        ) > 0
                    }
                    .forEach { occasion ->

                        MatchBar(
                            label = occasion,
                            value =
                                viewModel.occasionCount(
                                    items,
                                    occasion
                                ),
                            total = total,
                            color = Burgundy
                        )
                    }
            }
        }

        item {

            AnalysisCard(
                title = "Color Palette",
                subtitle =
                    "Your most-used outfit colors."
            ) {

                colorCounts
                    .entries
                    .sortedByDescending {
                        it.value
                    }
                    .take(5)
                    .forEach { entry ->

                        MatchBar(
                            label =
                                entry.key.ifBlank {
                                    "Unspecified"
                                },

                            value = entry.value,
                            total = total,
                            color = BoutiqueBlush
                        )
                    }

                if (items.isEmpty()) {

                    Text(
                        text =
                            "Add color labels to generate your palette.",
                        color = MutedText
                    )
                }
            }
        }

        item {

            AnalysisCard(
                title = "How a Match Is Chosen",
                subtitle =
                    "The recommendation engine combines these indicators with your saved outfits."
            ) {

                Text(
                    text =
                        "Occasion + selected color filter decide eligibility. Weather-compatible categories and colors associated with your mood increase an outfit's rank.",

                    color = MutedText
                )
            }
        }
    }
}