package com.caminaapps.bookworm.di

import com.caminaapps.bookworm.core.data.repository.BookRepositoryImpl
import com.caminaapps.bookworm.core.data.repository.OnlineSearchBookRepositoryImpl
import com.caminaapps.bookworm.core.domain.repository.BookRepository
import com.caminaapps.bookworm.core.domain.repository.OnlineSearchBookRepository
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
        onlineSearchBookRepositoryImpl: OnlineSearchBookRepositoryImpl
    ): OnlineSearchBookRepository

    @Binds
    @Singleton
    abstract fun bindBookRepository(
        bookRepositoryImpl: BookRepositoryImpl
    ): BookRepository
}