package com.hpyk.closet.domain.usecase

import com.hpyk.closet.data.repository.WardrobeRepository
import com.hpyk.closet.domain.model.WardrobeItem

class DeleteWardrobeItemUseCase(
    private val repository: WardrobeRepository
) {

    operator fun invoke(
        item: WardrobeItem
    ) {
        repository.deleteItem(item)
    }
}