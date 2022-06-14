package com.nextory.testapp.ui.bookdetails

import android.util.Log
import androidx.compose.material.rememberBottomDrawerState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import com.nextory.testapp.data.Book
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var currentBookId: Long? = null

    private val _selectedBook = mutableStateOf(Book(-1,"","","",""))
    val selectedBook: State<Book> = _selectedBook
    init {
        savedStateHandle.get<Long>("bookId")?.let { bookId ->
            if(bookId != -1L) {
                viewModelScope.launch {
                    bookRepository.getBookById(bookId)?.also { book->
                        currentBookId = book.id
                        _selectedBook.value = book
                    }
                }
            }
        }
    }

    fun onEvent(event: BookDetailsEvent){
        when(event){
            is BookDetailsEvent.OnFavoriteChange -> {
                viewModelScope.launch {
                   // bookRepository.updateBookFavorite(id = event.book.id, favorite = event.book.favorite)
                    val book = event.book
                    book.favorite = event.isFavorite
                    bookRepository.insertBook(book)
                }
            }
        }
    }

}