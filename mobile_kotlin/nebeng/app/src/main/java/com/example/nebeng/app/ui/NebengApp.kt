package com.example.nebeng.app.ui

import android.app.Application
import com.example.nebeng.core.session.data.UserPreferencesRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.maplibre.android.MapLibre
import org.maplibre.android.WellKnownTileServer
import javax.inject.Inject

@HiltAndroidApp
class NebengApp : Application() {

    companion object {
        lateinit var instance: NebengApp
            private set

        val appContext get() = instance.applicationContext
    }

    @Inject
    lateinit var userPrefsRepo: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        instance = this

        MapLibre.getInstance(this, null, WellKnownTileServer.MapLibre)

        // Preload DataStore secara sinkron ke RoleCache
        CoroutineScope(Dispatchers.IO).launch {
            val cachedRole = userPrefsRepo.userTypeFlow.first()
            val isLoggedIn = userPrefsRepo.isLoggedInFlow.first()
            RoleCache.role = cachedRole
            RoleCache.isLoggedIn = isLoggedIn
        }
    }
}

object RoleCache {
    var role: String? = null
    var isLoggedIn: Boolean = false
}
