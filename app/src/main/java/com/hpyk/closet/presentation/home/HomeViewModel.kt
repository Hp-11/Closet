package com.hpyk.closet.presentation.home

import androidx.lifecycle.ViewModel
import com.hpyk.closet.data.repository.WardrobeRepository

class HomeViewModel(
    repository: WardrobeRepository
) : ViewModel() {

    val wardrobe =
        repository.wardrobe
}