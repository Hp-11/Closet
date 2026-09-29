package com.hpyk.closet.presentation.wardrobe

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hpyk.closet.domain.model.WardrobeItem
import com.hpyk.closet.ui.theme.Burgundy
import com.hpyk.closet.ui.theme.MutedText

@Composable
fun WardrobeCard(
    item: WardrobeItem,
    onDelete: () -> Unit
) {

    val bitmap =
        remember(item.imagePath) {
            BitmapFactory.decodeFile(
                item.imagePath
            )
        }

    Card(
        colors =
            CardDefaults.cardColors()
    ) {

        bitmap?.let {

            Image(
                bitmap = it.asImageBitmap(),

                contentDescription =
                    item.category,

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(140.dp),

                contentScale =
                    ContentScale.Crop
            )
        }

        Column(
            modifier =
                Modifier.padding(10.dp)
        ) {

            Text(
                text = item.category,
                fontWeight = FontWeight.Bold
            )

            Text(
                text =
                    "${item.color} · ${item.occasion}",
                fontSize = 12.sp,
                color = MutedText
            )

            Text(
                text = "DELETE",
                color = Burgundy,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,

                modifier =
                    Modifier.padding(
                        top = 8.dp
                    )
            )
        }
    }
}