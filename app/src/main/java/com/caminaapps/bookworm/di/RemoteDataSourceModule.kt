package com.caminaapps.bookworm.di

import com.caminaapps.bookworm.core.data.network.OpenLibraryAPI
import com.caminaapps.bookworm.core.data.network.interceptor.LoggingInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient): OpenLibraryAPI {
        val contentType = "application/json".toMediaType()
        val converterFactory = json.asConverterFactory(contentType)
        return Retrofit.Builder()
            .baseUrl(OpenLibraryAPI.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(OpenLibraryAPI::class.java)
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(loggingInterceptor: LoggingInterceptor): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(loggingInterceptor)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}
