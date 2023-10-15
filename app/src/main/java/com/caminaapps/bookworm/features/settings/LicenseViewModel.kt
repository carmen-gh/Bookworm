package com.caminaapps.bookworm.features.settings

import androidx.lifecycle.ViewModel
import com.mikepenz.aboutlibraries.Libs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LicenseViewModel @Inject constructor(
    libs: Libs,
) : ViewModel() {
    val libraries = libs.libraries.map {
        LibInfo(
            name = it.name + " " + it.artifactVersion,
            license = it.licenses.first().licenseContent ?: "-"
        )
    }
}

data class LibInfo(
    val name: String,
    val license: String,
)
