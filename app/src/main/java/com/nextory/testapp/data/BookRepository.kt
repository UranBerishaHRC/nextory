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

    suspend fun getBookById(id: Long): Book? {
        return bookDao.getBookById(id)
    }

    suspend fun updateBookFavorite(id: Long, favorite:Boolean): Unit{
        return bookDao.updateBookFavorite(id = id, favorite = favorite)
    }

    suspend fun insertBook(book:Book): Unit{
        return bookDao.insertBook(book)
    }
}