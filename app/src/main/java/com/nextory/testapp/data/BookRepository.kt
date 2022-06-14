package com.nextory.testapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookDao: BookDao
) {
    fun observePagedBooks(pagingConfig: PagingConfig): Flow<PagingData<Book>> {
        return Pager(config = pagingConfig) {
            bookDao.observePagedBooks()
        }.flow
    }

    fun searchBooks(pagingConfig: PagingConfig, searchQuery: String): Flow<PagingData<Book>> {
        return Pager(config = pagingConfig) {
            bookDao.searchBooks(searchQuery = searchQuery)
        }.flow
    }

    suspend fun getBookById(id: Long): Book? {
        return bookDao.getBookById(id)
    }

    suspend fun updateBookFavorite(id: Long, favorite: Boolean): Unit {
        val isFavorite = if (favorite) 1 else 0
        return bookDao.updateBookFavorite(id = id, favorite = isFavorite)
    }

    suspend fun insertBook(book: Book): Unit {
        return bookDao.insertBook(book)
    }
}