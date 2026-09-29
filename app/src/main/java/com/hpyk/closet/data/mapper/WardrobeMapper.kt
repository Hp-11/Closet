package com.hpyk.closet.data.mapper

import com.hpyk.closet.data.local.WardrobeEntity
import com.hpyk.closet.domain.model.WardrobeItem

object WardrobeMapper {

    fun toDomain(
        entity: WardrobeEntity
    ): WardrobeItem {

        return WardrobeItem(
            id = entity.id,
            imagePath = entity.imagePath,
            gender = entity.gender,
            category = entity.category,
            color = entity.color,
            occasion = entity.occasion
        )
    }

    fun toEntity(
        item: WardrobeItem
    ): WardrobeEntity {

        return WardrobeEntity(
            id = item.id,
            imagePath = item.imagePath,
            gender = item.gender,
            category = item.category,
            color = item.color,
            occasion = item.occasion
        )
    }
}