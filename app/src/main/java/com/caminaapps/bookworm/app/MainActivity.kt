package com.caminaapps.bookworm.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAnalytics()

        setContent {
            setContent {
                MainScreen()
            }
        }
    }

    private fun initAnalytics() {
        firebaseAnalytics = Firebase.analytics
        val params = Bundle()
        params.putString("test", "test")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, params)
    }
}
