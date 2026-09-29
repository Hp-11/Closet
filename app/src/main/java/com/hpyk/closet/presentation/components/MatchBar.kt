package com.hpyk.closet.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hpyk.closet.ui.theme.MutedText
import com.hpyk.closet.ui.theme.SoftSurface

@Composable
fun MatchBar(
    label: String,
    value: Int,
    total: Int,
    color: Color
) {

    Row(
        modifier =
            Modifier.fillMaxWidth(),
        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Text(
            text = label,
            modifier =
                Modifier.weight(1f),
            fontSize = 14.sp
        )

        Text(
            text = "$value",
            fontSize = 13.sp,
            color = MutedText
        )
    }

    LinearProgressIndicator(
        progress = {
            value.toFloat() /
                    total.coerceAtLeast(1)
        },

        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    top = 4.dp,
                    bottom = 11.dp
                )
                .height(8.dp),

        color = color,
        trackColor = SoftSurface
    )
}