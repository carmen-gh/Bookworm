package com.caminaapps.bookworm.di

import com.caminaapps.bookworm.util.CrashlyticsLogging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Qualifier

typealias Logger = Timber.Tree

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProdLogger

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DebugLogger

@Module
@InstallIn(SingletonComponent::class)
object LoggingModule {
    @DebugLogger
    @Provides
    fun provideDebugLogger(): Logger = Timber.DebugTree()

    @ProdLogger
    @Provides
    fun provideProdLogger(): Logger = CrashlyticsLogging()
}
