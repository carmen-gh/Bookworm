package com.caminaapps.bookworm.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {

    @Query("SELECT * FROM book")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM book WHERE id = :bookId")
    fun getBook(bookId: String): Flow<BookEntity?>

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
