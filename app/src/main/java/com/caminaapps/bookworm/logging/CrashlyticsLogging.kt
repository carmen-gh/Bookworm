package com.caminaapps.bookworm.logging

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashlyticsLogging : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.log(message)

        if (priority == Log.ERROR) {
            if (t == null) {
                crashlytics.recordException(Throwable(message))
            } else {
                crashlytics.recordException(t)
            }
        }
    }
}
