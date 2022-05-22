package com.caminaapps.bookworm.core.data.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getAllBooksStream(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book WHERE id = :bookId")
    fun getBookStream(bookId: String): Flow<BookEntity?>

    @Insert(onConflict = REPLACE)
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
