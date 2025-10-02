package com.dong.reader.screens.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.room.util.TableInfo
import coil3.compose.rememberAsyncImagePainter
import com.dong.reader.components.InputField
import com.dong.reader.components.ReaderAppBar
import com.dong.reader.model.MBook

@Preview
@Composable
fun BookSearchScreen(navController: NavController = NavController(context = LocalContext.current)) {
    Scaffold(
        topBar = {
            ReaderAppBar(
                title = "Search Books",
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                navController = navController,
                showProfile = false
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column {
                SearchForm(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) { }
                Spacer(modifier = Modifier.height(13.dp))
                BookList(navController)
            }
        }
    }

}

@Composable
fun BookList(navController: NavController) {
    val listOfBooks = listOf(
        MBook(
            id = "dadfa",
            title = "Hello Again 1",
            authors = "All of us",
            notes = "",
            photoUrl = "https://books.google.com/books/content?id=eR4kCQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
            categories = "",
            publishedDate = "",
            pageCount = ""
        ),
        MBook(
            id = "dadfa",
            title = "Hello Again 2",
            authors = "All of us",
            notes = "",
            photoUrl = "",
            categories = "",
            publishedDate = "",
            pageCount = ""
        ),
        MBook(
            id = "dadfa",
            title = "Hello Again 3",
            authors = "All of us",
            notes = "",
            photoUrl = "",
            categories = "",
            publishedDate = "",
            pageCount = ""
        ),
        MBook(
            id = "dadfa",
            title = "Hello Again 4",
            authors = "All of us",
            notes = "",
            photoUrl = "",
            categories = "",
            publishedDate = "",
            pageCount = ""
        ),
        MBook(
            id = "dadfa",
            title = "Hello Again 5",
            authors = "All of us",
            notes = "",
            photoUrl = "",
            categories = "",
            publishedDate = "",
            pageCount = ""
        ),
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(listOfBooks.size) {
            BookRow(book = listOfBooks[it], navController = navController)
        }
    }
}

@Composable
fun BookRow(book: MBook, navController: NavController) {
    Card(
        modifier = Modifier
            .clickable {}
            .fillMaxWidth()
            .height(100.dp)
            .padding(3.dp),
        shape = RectangleShape,
        elevation =  CardDefaults.cardElevation(defaultElevation = 7.dp)
    ) {
        Row (
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                modifier = Modifier.width(80.dp).fillMaxHeight().padding(end = 4.dp),
                painter = rememberAsyncImagePainter(book.photoUrl),
                contentDescription = "book image",
            )
            Column {
                Text(text = book.title!!, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.titleMedium)
                Text(text = "Author: ${book.authors}", overflow = TextOverflow.Clip,
                    style = MaterialTheme.typography.labelMedium)
                Text(text = "Date: ${book.publishedDate}", overflow = TextOverflow.Clip,
                    style = MaterialTheme.typography.labelMedium)
            }

        }
    }
}


@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    hint: String = "Search",
    onSearch: (String) -> Unit = {}
) {
    Column {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value) {
            searchQueryState.value.trim().isNotEmpty()
        }

        InputField(
            valueState = searchQueryState,
            labelId = "Search",
            enabled = true,
            onAction = KeyboardActions {
                Log.d("dong", "search $valid")
                if (!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            })

    }
}
