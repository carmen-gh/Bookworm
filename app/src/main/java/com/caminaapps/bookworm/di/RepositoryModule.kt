package com.caminaapps.bookworm.di

import com.caminaapps.bookworm.core.data.repository.BookRepository
import com.caminaapps.bookworm.core.data.repository.DefaultBookRepository
import com.caminaapps.bookworm.core.data.repository.OnlineSearchBookRepository
import com.caminaapps.bookworm.core.data.repository.OpenLibrarySearchBookRepository
import com.caminaapps.bookworm.core.data.repository.UserPreferencesRepository
import com.caminaapps.bookworm.core.data.repository.DefaultUserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindOnlineSearchBookRepository(
        onlineSearchBookRepositoryImpl: OpenLibrarySearchBookRepository
    ): OnlineSearchBookRepository

    @Binds
    @Singleton
    abstract fun bindBookRepository(
        bookRepositoryImpl: DefaultBookRepository
    ): BookRepository

    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(
        userPreferencesRepositoryImpl: DefaultUserPreferencesRepository
    ): UserPreferencesRepository
}
