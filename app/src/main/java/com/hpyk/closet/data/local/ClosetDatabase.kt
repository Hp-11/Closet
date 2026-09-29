package com.hpyk.closet.data.local

import android.content.Context

class ClosetDatabase(
    context: Context
) {

    val wardrobeDao =
        WardrobeDao(context)
}