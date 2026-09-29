package com.hpyk.closet.data.local

import android.content.Context
import com.hpyk.closet.core.constants.ClosetConstants

class WardrobeDao(
    context: Context
) {

    private val preferences =
        context.getSharedPreferences(
            ClosetConstants.PREFS_NAME,
            Context.MODE_PRIVATE
        )

    fun getAll(): List<WardrobeEntity> {

        val result = mutableListOf<WardrobeEntity>()

        for (
        id in 1L..ClosetConstants.MAX_WARDROBE_ITEMS
        ) {

            val imagePath =
                preferences.getString(
                    "$id.imagePath",
                    null
                ) ?: continue

            result += WardrobeEntity(
                id = id,
                imagePath = imagePath,
                gender = preferences.getString(
                    "$id.gender",
                    "Unspecified"
                ) ?: "Unspecified",

                category = preferences.getString(
                    "$id.category",
                    "Uncategorized"
                ) ?: "Uncategorized",

                color = preferences.getString(
                    "$id.color",
                    "Unspecified"
                ) ?: "Unspecified",

                occasion = preferences.getString(
                    "$id.occasion",
                    "Casual"
                ) ?: "Casual"
            )
        }

        return result
    }

    fun insert(
        entity: WardrobeEntity
    ) {

        preferences.edit()
            .putString(
                "${entity.id}.imagePath",
                entity.imagePath
            )
            .putString(
                "${entity.id}.gender",
                entity.gender
            )
            .putString(
                "${entity.id}.category",
                entity.category
            )
            .putString(
                "${entity.id}.color",
                entity.color
            )
            .putString(
                "${entity.id}.occasion",
                entity.occasion
            )
            .apply()
    }

    fun delete(
        id: Long
    ) {

        preferences.edit()
            .remove("$id.imagePath")
            .remove("$id.gender")
            .remove("$id.category")
            .remove("$id.color")
            .remove("$id.occasion")
            .apply()
    }

    fun findAvailableId(): Long? {

        for (
        id in 1L..ClosetConstants.MAX_WARDROBE_ITEMS
        ) {

            val exists =
                preferences.contains(
                    "$id.imagePath"
                )

            if (!exists) {
                return id
            }
        }

        return null
    }
}