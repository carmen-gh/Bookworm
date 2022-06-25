package com.caminaapps.bookworm.di

import android.content.Context
import com.caminaapps.bookworm.core.data.database.AppDatabase
import com.caminaapps.bookworm.core.data.database.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    fun provideBookDao(appDatabase: AppDatabase): BookDao = appDatabase.bookDao()
}
