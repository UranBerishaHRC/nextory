package com.nextory.testapp.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun observePagedBooks(): PagingSource<Int, Book>

    @Query("SELECT * FROM book WHERE id = :id")
    suspend fun getBookById(id: Long): Book?

    @Query("UPDATE book SET favorite= :favorite WHERE id=:id")
    suspend fun updateBookFavorite(id: Long, favorite:Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Query("SELECT * FROM book WHERE author || title LIKE '%' || :searchQuery || '%'")
    fun searchBooks(searchQuery: String):PagingSource<Int, Book>

}