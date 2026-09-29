package com.hpyk.closet.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hpyk.closet.ui.theme.Burgundy

@Composable
fun BoutiqueButton(
    label: String,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {

    Button(
        onClick = action,

        modifier =
            modifier.fillMaxWidth(),

        colors =
            ButtonDefaults.buttonColors(
                containerColor = Burgundy
            ),

        shape =
            RoundedCornerShape(28.dp)
    ) {

        Text(
            text = label,
            fontWeight = FontWeight.Bold,

            modifier =
                Modifier.padding(
                    vertical = 4.dp
                )
        )
    }
}