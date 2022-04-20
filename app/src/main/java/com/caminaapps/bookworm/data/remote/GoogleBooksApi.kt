package com.caminaapps.bookworm.data.remote

import com.caminaapps.bookworm.data.remote.dto.SearchResultDTO
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface GoogleBooksApi {

    @GET("volumes/")
    suspend fun getBooks(@QueryMap(encoded = true) options: Map<String, String>): SearchResultDTO


    companion object {
        const val BASE_URL = "https://www.googleapis.com/books/v1/"

        private const val QUERY_KEY = "q"
        private const val MAX_RESULTS_KEY = "maxResults"

        fun parameterISBN(isbn: String) = mapOf(
            QUERY_KEY to "$isbn+isbn",
            MAX_RESULTS_KEY to "1"
        )

        fun parameterTitle(title: String) = mapOf(
            QUERY_KEY to "$title+intitle",
            MAX_RESULTS_KEY to "10"
        )
    }
}