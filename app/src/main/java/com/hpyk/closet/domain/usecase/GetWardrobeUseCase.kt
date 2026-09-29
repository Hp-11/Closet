package com.hpyk.closet.domain.usecase

import com.hpyk.closet.data.repository.WardrobeRepository

class GetWardrobeUseCase(
    private val repository: WardrobeRepository
) {

    operator fun invoke() =
        repository.wardrobe
}