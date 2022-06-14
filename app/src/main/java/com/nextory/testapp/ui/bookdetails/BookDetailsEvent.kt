package com.nextory.testapp.ui.bookdetails

import com.nextory.testapp.data.Book

sealed class BookDetailsEvent {
    data class OnFavoriteChange(val book: Book, val isFavorite: Boolean): BookDetailsEvent()
}
