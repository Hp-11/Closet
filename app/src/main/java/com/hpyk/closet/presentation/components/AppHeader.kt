package com.hpyk.closet.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hpyk.closet.ui.theme.Burgundy
import com.hpyk.closet.ui.theme.Charcoal

@Composable
fun AppHeader(
    onStyleClick: () -> Unit
) {

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme
                        .colorScheme
                        .surface
                )
                .padding(
                    horizontal = 22.dp,
                    vertical = 16.dp
                ),

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Box(
            modifier =
                Modifier
                    .size(34.dp)
                    .background(
                        Burgundy,
                        androidx.compose.foundation.shape
                            .RoundedCornerShape(50)
                    )
        )

        Spacer(
            modifier =
                Modifier.width(10.dp)
        )

        Text(
            text = "CLOSET",
            fontSize = 26.sp,
            fontWeight = FontWeight.Black,
            color = Charcoal,
            letterSpacing = 2.sp
        )

        Spacer(
            modifier =
                Modifier.weight(1f)
        )

        Text(
            text = "YOUR STYLE",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Burgundy,

            modifier =
                Modifier
                    .clickable {
                        onStyleClick()
                    }
                    .padding(8.dp)
        )
    }
}