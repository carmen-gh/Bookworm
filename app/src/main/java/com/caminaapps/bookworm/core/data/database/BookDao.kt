package com.caminaapps.bookworm.core.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM book ORDER BY added_date ASC")
    fun getAllBooksStreamSortedByDateAsc(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book ORDER BY added_date DESC")
    fun getAllBooksStreamSortedByDateDesc(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book ORDER BY title ASC")
    fun getAllBooksStreamSortedByTitleAsc(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book ORDER BY title DESC")
    fun getAllBooksStreamSortedByTitleDesc(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book ORDER BY author ASC")
    fun getAllBooksStreamSortedByAuthorAsc(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book ORDER BY author DESC")
    fun getAllBooksStreamSortedByAuthorDesc(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book WHERE id = :bookId")
    fun getBookStream(bookId: String): Flow<BookEntity?>

    @Upsert
    suspend fun insertBook(book: BookEntity)

    @Update
    suspend fun updateBook(book: BookEntity)

    @Query("DELETE FROM book WHERE id = :bookId")
    suspend fun deleteById(bookId: String)

    @Delete
    suspend fun delete(book: BookEntity)

    @Query("DELETE FROM book")
    suspend fun deleteAll()
}
