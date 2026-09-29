
package com.hpyk.closet.presentation.upload

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hpyk.closet.core.constants.ClosetConstants
import com.hpyk.closet.presentation.components.BoutiqueButton
import com.hpyk.closet.presentation.components.ChoiceField
import com.hpyk.closet.presentation.components.PhotoPlaceholder
import com.hpyk.closet.ui.theme.Burgundy
import com.hpyk.closet.ui.theme.MutedText
import com.hpyk.closet.presentation.components.ColorPickerField


@Composable
fun UploadScreen(
    viewModel: UploadViewModel,
    onCamera: () -> Unit,
    onGallery: () -> Unit,
    onSaved: () -> Unit
) {

    val state = viewModel.uiState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        // ---------------------------------------------------------
        // HEADER
        // ---------------------------------------------------------

        Text(
            text = "Upload Your Wardrobe",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = "Add a piece and organize it your way.",
            color = MutedText
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // ---------------------------------------------------------
        // PHOTO
        // ---------------------------------------------------------

        if (state.photo == null) {

            PhotoPlaceholder()

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                OutlinedButton(
                    onClick = onCamera,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("TAKE PHOTO")
                }

                OutlinedButton(
                    onClick = onGallery,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("CHOOSE PHOTO")
                }
            }

        } else {

            Image(
                bitmap = state.photo.asImageBitmap(),
                contentDescription = "New wardrobe item",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            OutlinedButton(
                onClick = onGallery,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("CHANGE PHOTO")
            }
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // ---------------------------------------------------------
        // GENDER
        // ---------------------------------------------------------

        OutlinedTextField(
            value = state.gender,
            onValueChange = viewModel::setGender,
            label = {
                Text("Gender")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // ---------------------------------------------------------
        // CATEGORY
        // ---------------------------------------------------------

        ChoiceField(
            label = "Category",
            selection = state.category,
            choices = ClosetConstants.categories,
            onSelect = viewModel::setCategory
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // ---------------------------------------------------------
        // COLOR
        // ---------------------------------------------------------

//        OutlinedTextField(
//            value = state.color,
//            onValueChange = viewModel::setColor,
//            label = {
//                Text("Color")
//            },
//            modifier = Modifier.fillMaxWidth()
//        )

        ColorPickerField(
            selectedColor = state.color,
            onColorSelected = viewModel::setColor
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // ---------------------------------------------------------
        // OCCASION
        // ---------------------------------------------------------

        ChoiceField(
            label = "Occasion",
            selection = state.occasion,
            choices = ClosetConstants.occasions,
            onSelect = viewModel::setOccasion
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // ---------------------------------------------------------
        // SAVE / UPLOAD
        // ---------------------------------------------------------

        BoutiqueButton(
            label = if (state.isSaving) {
                "SAVING..."
            } else {
                "SAVE ITEM"
            },

            action = {

                if (!state.isSaving) {

                    if (viewModel.save()) {
                        onSaved()
                    }
                }
            }
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // ---------------------------------------------------------
        // STATUS
        // ---------------------------------------------------------

        state.status?.let {

            Text(
                text = it,
                color = Burgundy,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        // Extra bottom spacing so the button isn't
        // hidden behind navigation bars.
        Spacer(
            modifier = Modifier.height(30.dp)
        )
    }
}