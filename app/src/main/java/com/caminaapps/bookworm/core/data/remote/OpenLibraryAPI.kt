package com.caminaapps.bookworm.core.data.remote

import com.caminaapps.bookworm.core.data.remote.dto.SearchResultDTO
import retrofit2.http.GET
import retrofit2.http.Query


interface OpenLibraryAPI {

    // Search by ISBN
    // curl "https://openlibrary.org/search.json?limit=10&isbn=9783880104822"
    @GET("search.json")
    suspend fun searchBookByIsbn(
        @Query("isbn") isbn: String,
        @Query("limit") limit: Int = 1
    ): SearchResultDTO


    // Search by Title
    // curl "https://openlibrary.org/search.json?title=ein%20hund%20namens%20money&limit=10"
    @GET("search.json")
    suspend fun searchBookByTitle(
        @Query("title", encoded = true) title: String,
        @Query("limit") limit: Int = 10
    ): SearchResultDTO


    companion object {
        const val BASE_URL = "https://openlibrary.org/"
    }
}
