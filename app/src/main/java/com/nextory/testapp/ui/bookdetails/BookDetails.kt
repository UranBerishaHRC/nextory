package com.nextory.testapp.ui.bookdetails

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nextory.testapp.R
import com.nextory.testapp.data.Book

@Composable
fun BookDetails(
    navController: NavController,
    viewModel: BookDetailsViewModel = hiltViewModel()
) {
    val selectedBook = viewModel.selectedBook.value
    BookDetailsScreen(navController = navController, book = selectedBook)
}


@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
private fun BookDetailsScreen(
    navController: NavController,
    book: Book
) {
    Scaffold(
        topBar = { BookDetailsTopBar(navController = navController, title = book.title) }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally


        ) {
            Spacer(modifier = Modifier.height(30.dp))
            AsyncImage(
                model = book.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(140.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(), text = book.author
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(book.description)

        }

    }

}

@Composable
private fun BookDetailsTopBar(
    navController: NavController,
    title: String
) {
    CenterAlignedTopAppBar(
        //todo handle long texts
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = {navController.navigateUp()}) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(
                WindowInsetsSides.Horizontal + WindowInsetsSides.Top
            )
        )
    )
}