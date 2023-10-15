package com.caminaapps.bookworm.di

import android.content.Context
import com.caminaapps.bookworm.R
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.util.withJson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLibs(@ApplicationContext context: Context) =
        Libs.Builder().withJson(context, R.raw.aboutlibraries).build()
}
