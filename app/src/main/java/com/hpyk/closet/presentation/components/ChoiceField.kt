package com.hpyk.closet.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ChoiceField(
    label: String,
    selection: String,
    choices: List<String>,
    onSelect: (String) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Box {

        OutlinedButton(
            onClick = {
                expanded = true
            },

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text(
                text = "$label: $selection",
                modifier =
                    Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            choices.forEach { choice ->

                DropdownMenuItem(
                    text = {
                        Text(choice)
                    },

                    onClick = {
                        onSelect(choice)
                        expanded = false
                    }
                )
            }
        }
    }
}