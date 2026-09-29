
package com.hpyk.closet.presentation.upload

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hpyk.closet.ClosetApplication
import com.hpyk.closet.autodetection.AutoDetectionManager
import com.hpyk.closet.domain.usecase.AddWardrobeItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UploadViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository =
        (application as ClosetApplication)
            .repository

    private val addWardrobeItem =
        AddWardrobeItemUseCase(repository)

    private val autoDetectionManager =
        AutoDetectionManager()

    var uiState =
        mutableStateOf(
            UploadUiState()
        )
        private set

    /**
     * Called whenever a new clothing photo is selected.
     *
     * The photo is immediately shown in the UI while
     * auto-detection runs in the background.
     */
    fun setPhoto(
        bitmap: Bitmap
    ) {

        uiState.value =
            uiState.value.copy(
                photo = bitmap,
                status = null,
                isAnalyzing = true
            )

        viewModelScope.launch {

            val detectionResult =
                withContext(Dispatchers.Default) {

                    autoDetectionManager.analyze(
                        bitmap
                    )
                }

            val currentState =
                uiState.value

            uiState.value =
                currentState.copy(

                    isAnalyzing = false,

                    // Only populate fields when
                    // the detector actually returned
                    // a value.
                    color =
                        detectionResult.color
                            ?: currentState.color,

                    category =
                        detectionResult.category
                            ?: currentState.category,

                    occasion =
                        detectionResult.occasion
                            ?: currentState.occasion
                )
        }
    }

    /**
     * Handles gallery-selected images.
     */
    fun setPhotoFromUri(
        uri: Uri
    ) {

        val bitmap =
            getApplication<Application>()
                .contentResolver
                .openInputStream(uri)
                ?.use {

                    android.graphics.BitmapFactory
                        .decodeStream(it)
                }

        bitmap?.let {

            setPhoto(it)

        } ?: run {

            setCameraError(
                "Unable to load this image."
            )
        }
    }

    fun setGender(
        value: String
    ) {

        uiState.value =
            uiState.value.copy(
                gender = value
            )
    }

    fun setCategory(
        value: String
    ) {

        uiState.value =
            uiState.value.copy(
                category = value
            )
    }

    fun setColor(
        value: String
    ) {

        uiState.value =
            uiState.value.copy(
                color = value
            )
    }

    fun setOccasion(
        value: String
    ) {

        uiState.value =
            uiState.value.copy(
                occasion = value
            )
    }

    /**
     * Saves the currently configured wardrobe item.
     */
    fun save(): Boolean {

        val state =
            uiState.value

        if (state.photo == null) {

            uiState.value =
                state.copy(
                    status =
                        "Choose a photo first."
                )

            return false
        }

        if (state.color.isBlank()) {

            uiState.value =
                state.copy(
                    status =
                        "Choose the clothing color."
                )

            return false
        }

        uiState.value =
            state.copy(
                isSaving = true,
                status = null
            )

        val success =
            addWardrobeItem(
                bitmap = state.photo,
                gender = state.gender,
                category = state.category,
                color = state.color,
                occasion = state.occasion
            )

        uiState.value =
            if (success) {

                UploadUiState()

            } else {

                state.copy(
                    isSaving = false,
                    status =
                        "Your local closet is full."
                )
            }

        return success
    }

    fun setCameraError(
        message: String
    ) {

        uiState.value =
            uiState.value.copy(
                status = message
            )
    }

    fun clearPhoto() {

        uiState.value =
            uiState.value.copy(
                photo = null,
                isAnalyzing = false
            )
    }
}
