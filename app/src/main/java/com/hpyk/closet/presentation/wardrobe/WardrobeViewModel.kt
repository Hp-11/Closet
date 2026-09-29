package com.hpyk.closet.presentation.wardrobe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.hpyk.closet.ClosetApplication
import com.hpyk.closet.domain.model.WardrobeItem
import com.hpyk.closet.domain.usecase.DeleteWardrobeItemUseCase

class WardrobeViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository =
        (application as ClosetApplication)
            .repository

    private val deleteWardrobeItem =
        DeleteWardrobeItemUseCase(repository)

    val wardrobe =
        repository.wardrobe

    fun delete(
        item: WardrobeItem
    ) {
        deleteWardrobeItem(item)
    }
}