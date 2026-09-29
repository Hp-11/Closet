package com.hpyk.closet.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hpyk.closet.core.constants.ClosetConstants
import com.hpyk.closet.domain.model.WardrobeItem
import com.hpyk.closet.presentation.components.DashboardCard
import com.hpyk.closet.ui.theme.BoutiqueBlush
import com.hpyk.closet.ui.theme.Burgundy
import com.hpyk.closet.ui.theme.Charcoal
import com.hpyk.closet.ui.theme.MutedText
import com.hpyk.closet.ui.theme.SoftSurface

@Composable
fun HomeScreen(
    items: List<WardrobeItem>,
    onUpload: () -> Unit,
    onRecommend: () -> Unit,
    onStyle: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftSurface),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        item {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Burgundy)
                    .padding(32.dp),

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Welcome to your closet",
                    color =
                        MaterialTheme
                            .colorScheme
                            .onPrimary,

                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )

                Text(
                    text =
                        "Digitize your wardrobe and find a look for every occasion.",

                    color = BoutiqueBlush,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(
                modifier =
                    Modifier.height(22.dp)
            )
        }

        item {

            DashboardCard(
                title = "Upload Your Wardrobe",
                description =
                    "Add an outfit from your camera or gallery.",

                label = "UPLOAD",
                action = onUpload
            )
        }

        item {

            DashboardCard(
                title = "Get Recommendations",
                description =
                    "Find pieces that match your occasion.",

                label = "RECOMMEND",
                action = onRecommend
            )
        }

        item {

            HelpCard()
        }

        item {

            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme
                                .colorScheme
                                .surface
                    )
            ) {

                Column(
                    modifier =
                        Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Wardrobe Insights",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Charcoal
                    )

                    Text(
                        text =
                            "${items.size} saved item" +
                                    if (items.size == 1)
                                        ""
                                    else
                                        "s" +
                                                " · " +
                                                "${items.map { it.occasion }.distinct().size} occasions covered",

                        color = MutedText
                    )
                }
            }
        }
    }
}

@Composable
private fun HelpCard() {

    var showHelp by remember {
        mutableStateOf(false)
    }

    DashboardCard(
        title = "Need help?",
        description =
            "Learn how to navigate and get the best recommendations.",

        label = "HOW TO USE CLOSET"
    ) {
        showHelp = true
    }

    if (showHelp) {

        AlertDialog(
            onDismissRequest = {
                showHelp = false
            },

            title = {
                Text(
                    text = "How to use Closet",
                    fontWeight = FontWeight.Bold
                )
            },

            text = {

                Column(
                    verticalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {

                    Text(
                        "1. Tap Upload to take or choose a clothing photo."
                    )

                    Text(
                        "2. Add its category, color, and occasion."
                    )

                    Text(
                        "3. Open Recommend and choose occasion, weather, mood, and color."
                    )

                    Text(
                        "4. Tap YOUR STYLE to view wardrobe insights."
                    )

                    Text(
                        "5. Use the bottom navigation anytime."
                    )
                }
            },

            confirmButton = {

                TextButton(
                    onClick = {
                        showHelp = false
                    }
                ) {
                    Text(
                        text = "GOT IT",
                        color = Burgundy
                    )
                }
            }
        )
    }
}