
package com.hpyk.closet.presentation.upload

import android.graphics.Bitmap

data class UploadUiState(
    val photo: Bitmap? = null,

    val gender: String = "Unisex",

    val category: String = "Shirt",

    val color: String = "",

    val occasion: String = "Casual",

    val status: String? = null,

    val isSaving: Boolean = false,

    val isAnalyzing: Boolean = false
)
