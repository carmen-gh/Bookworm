package com.caminaapps.bookworm.app

import android.app.Application
import com.caminaapps.bookworm.BuildConfig
import com.caminaapps.bookworm.di.DebugLogger
import com.caminaapps.bookworm.di.Logger
import com.caminaapps.bookworm.di.ProdLogger
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import org.jetbrains.annotations.VisibleForTesting
import timber.log.Timber


@HiltAndroidApp
class BookwormApplication : Application() {

    @DebugLogger
    @Inject
    lateinit var debugLogger: Logger

    @ProdLogger
    @Inject
    lateinit var prodLogger: Logger


    override fun onCreate() {
        super.onCreate()
        initLogger(BuildConfig.DEBUG)
        initAppCheck()
    }

    @VisibleForTesting
    private fun initLogger(isDebug: Boolean) {
        val logger = if (isDebug) {
            debugLogger
        } else {
            prodLogger
        }
        Timber.plant(logger)
    }

    private fun initAppCheck() {
        FirebaseApp.initializeApp(this)
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance()
        )
    }
}
