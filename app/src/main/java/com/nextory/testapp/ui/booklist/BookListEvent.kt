package com.nextory.testapp.ui.booklist

import com.nextory.testapp.data.Book

sealed class BookListEvent {
    data class OnBookClick(val book: Book): BookListEvent()
}
