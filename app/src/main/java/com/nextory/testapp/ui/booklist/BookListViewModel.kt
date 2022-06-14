package com.nextory.testapp.ui.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import com.nextory.testapp.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    val pagedBooks = bookRepository.observePagedBooks(PAGING_CONFIG)

    companion object {
        val PAGING_CONFIG = PagingConfig(
            pageSize = 12,
            enablePlaceholders = false
        )
    }

    fun onEvent(event: BookListEvent){
        when(event){
            is BookListEvent.OnSearch -> {
                viewModelScope.launch {
                    //bookRepository.searchBooks(PAGING_CONFIG,event.searchQuery)
                }
            }
        }
    }
}