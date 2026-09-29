package com.hpyk.closet.domain.usecase

import android.graphics.Bitmap
import com.hpyk.closet.data.repository.WardrobeRepository

class AddWardrobeItemUseCase(
    private val repository: WardrobeRepository
) {

    operator fun invoke(
        bitmap: Bitmap,
        gender: String,
        category: String,
        color: String,
        occasion: String
    ): Boolean {

        return repository.addItem(
            bitmap = bitmap,
            gender = gender,
            category = category,
            color = color,
            occasion = occasion
        )
    }
}