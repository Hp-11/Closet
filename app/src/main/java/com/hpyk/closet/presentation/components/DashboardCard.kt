package com.hpyk.closet.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hpyk.closet.ui.theme.Charcoal
import com.hpyk.closet.ui.theme.MutedText

@Composable
fun DashboardCard(
    title: String,
    description: String,
    label: String,
    action: () -> Unit
) {

    Card(
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .fillMaxWidth(),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme
                        .colorScheme
                        .surface
            ),

        elevation =
            CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier =
                Modifier.padding(22.dp)
        ) {

            Text(
                text = title,
                fontWeight =
                    androidx.compose.ui.text.font.FontWeight.Bold,
                fontSize = 21.sp,
                color = Charcoal
            )

            Spacer(
                modifier =
                    Modifier.height(6.dp)
            )

            Text(
                text = description,
                color = MutedText
            )

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            BoutiqueButton(
                label = label,
                action = action
            )
        }
    }
}