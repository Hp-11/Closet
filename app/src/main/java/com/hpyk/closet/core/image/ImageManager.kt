package com.hpyk.closet.core.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.hpyk.closet.core.constants.ClosetConstants
import java.io.File
import java.io.FileOutputStream

class ImageManager(
    private val context: Context
) {

    private val imageDirectory: File
        get() =
            File(
                context.filesDir,
                ClosetConstants.IMAGE_DIRECTORY
            ).apply {
                if (!exists()) {
                    mkdirs()
                }
            }

    fun saveBitmap(
        bitmap: Bitmap,
        fileName: String
    ): String {

        val file = File(
            imageDirectory,
            fileName
        )

        FileOutputStream(file).use { outputStream ->

            bitmap.compress(
                Bitmap.CompressFormat.JPEG,
                90,
                outputStream
            )
        }

        return file.absolutePath
    }

    fun copyUriToInternalStorage(
        uri: Uri,
        fileName: String
    ): String? {

        return try {

            val bitmap =
                context.contentResolver
                    .openInputStream(uri)
                    ?.use {
                        BitmapFactory.decodeStream(it)
                    }

            bitmap?.let {
                saveBitmap(
                    bitmap = it,
                    fileName = fileName
                )
            }

        } catch (exception: Exception) {

            exception.printStackTrace()

            null
        }
    }

    fun loadBitmap(
        path: String
    ): Bitmap? {

        return BitmapFactory.decodeFile(path)
    }

    fun deleteImage(
        path: String
    ): Boolean {

        return try {
            File(path).delete()
        } catch (exception: Exception) {
            false
        }
    }
}