package com.hpyk.closet.data.repository

import android.content.Context
import com.hpyk.closet.core.image.ImageManager
import com.hpyk.closet.data.local.ClosetDatabase
import com.hpyk.closet.data.mapper.WardrobeMapper
import com.hpyk.closet.domain.model.WardrobeItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WardrobeRepository(
    private val database: ClosetDatabase,
    context: Context
) {

    private val dao =
        database.wardrobeDao

    private val imageManager =
        ImageManager(context)

    private val _wardrobe =
        MutableStateFlow(
            loadInitialWardrobe()
        )

    val wardrobe: StateFlow<List<WardrobeItem>> =
        _wardrobe.asStateFlow()

    private fun loadInitialWardrobe():
            List<WardrobeItem> {

        return dao
            .getAll()
            .map(WardrobeMapper::toDomain)
    }

    fun addItem(
        bitmap: android.graphics.Bitmap,
        gender: String,
        category: String,
        color: String,
        occasion: String
    ): Boolean {

        val id =
            dao.findAvailableId()
                ?: return false

        val imagePath =
            imageManager.saveBitmap(
                bitmap = bitmap,
                fileName = "wardrobe_$id.jpg"
            )

        val item =
            WardrobeItem(
                id = id,
                imagePath = imagePath,
                gender = gender,
                category = category,
                color = color,
                occasion = occasion
            )

        dao.insert(
            WardrobeMapper.toEntity(item)
        )

        _wardrobe.value =
            _wardrobe.value + item

        return true
    }

    fun addImageFromPath(
        imagePath: String,
        gender: String,
        category: String,
        color: String,
        occasion: String
    ): Boolean {

        val id =
            dao.findAvailableId()
                ?: return false

        val newPath =
            imageManager.saveBitmap(
                bitmap =
                    imageManager.loadBitmap(imagePath)
                        ?: return false,

                fileName = "wardrobe_$id.jpg"
            )

        val item =
            WardrobeItem(
                id = id,
                imagePath = newPath,
                gender = gender,
                category = category,
                color = color,
                occasion = occasion
            )

        dao.insert(
            WardrobeMapper.toEntity(item)
        )

        _wardrobe.value =
            _wardrobe.value + item

        return true
    }

    fun deleteItem(
        item: WardrobeItem
    ) {

        imageManager.deleteImage(
            item.imagePath
        )

        dao.delete(
            item.id
        )

        _wardrobe.value =
            _wardrobe.value.filter {
                it.id != item.id
            }
    }
}