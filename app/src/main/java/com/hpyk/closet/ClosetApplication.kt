package com.hpyk.closet

import android.app.Application
import com.hpyk.closet.data.local.ClosetDatabase
import com.hpyk.closet.data.repository.WardrobeRepository

class ClosetApplication : Application() {

    lateinit var database: ClosetDatabase
        private set

    lateinit var repository: WardrobeRepository
        private set

    override fun onCreate() {
        super.onCreate()

        database = ClosetDatabase(this)

        repository = WardrobeRepository(
            database = database,
            context = this
        )
    }
}