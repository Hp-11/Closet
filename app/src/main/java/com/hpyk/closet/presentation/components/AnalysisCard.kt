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
fun AnalysisCard(
    title: String,
    subtitle: String,
    content: @Composable () -> Unit
) {

    Card(
        modifier =
            Modifier
                .padding(bottom = 14.dp)
                .fillMaxWidth(),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    MaterialTheme
                        .colorScheme
                        .surface
            ),

        elevation =
            CardDefaults.cardElevation(3.dp)
    ) {

        Column(
            modifier =
                Modifier.padding(18.dp)
        ) {

            Text(
                text = title,
                fontWeight =
                    androidx.compose.ui.text.font.FontWeight.Bold,
                fontSize = 19.sp,
                color = Charcoal
            )

            Spacer(
                modifier =
                    Modifier.height(4.dp)
            )

            Text(
                text = subtitle,
                color = MutedText,
                fontSize = 13.sp
            )

            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            content()
        }
    }
}