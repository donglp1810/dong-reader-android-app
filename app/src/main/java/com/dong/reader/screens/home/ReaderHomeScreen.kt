package com.dong.reader.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.dong.reader.components.FABContent
import com.dong.reader.components.ListCard
import com.dong.reader.components.ReaderAppBar
import com.dong.reader.components.TitleSection
import com.dong.reader.model.MBook
import com.dong.reader.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth


@Composable
fun Home(navController: NavHostController) {
    Scaffold(
        topBar = {
            ReaderAppBar("A.Reader", navController = navController)
        },
        floatingActionButton = {
            FABContent {
                navController.navigate(ReaderScreens.SearchScreen.name)
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HomeContent(navController)

        }
    }
}

@Composable
fun HomeContent(navController: NavHostController) {
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


    val currentUserName = if (!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty())
        FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0) else "N/A"

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Row(modifier = Modifier.align(alignment = Alignment.Start)) {
                TitleSection(title = "Your Reading \n" + "activity right now...")
                Spacer(modifier = Modifier.fillMaxWidth(0.7f))
                Column {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Profile",
                        modifier = Modifier
                            .clickable {
                                navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                            }
                            .size(45.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = currentUserName.toString(),
                        modifier = Modifier.padding(2.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Magenta.copy(alpha = 0.5f),
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Clip,
                    )
                    HorizontalDivider()
                }
            }
            ReadingRightNowArea(books = listOf(), navController = navController)
            TitleSection(title = "Reading List")
            BookListArea(listOfBook = listOfBooks, navController = navController)
        }
    }
}

@Composable
fun BookListArea(listOfBook: List<MBook>, navController: NavController) {
    HorizontalScrollableComponent(listOfBook) {

    }

}

@Composable
fun HorizontalScrollableComponent(listOfBook: List<MBook>, onCardPressed: (String) -> Unit) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(280.dp)
            .horizontalScroll(scrollState)
    ) {
        for (book in listOfBook) {
            ListCard(book) {
                onCardPressed(it)
            }
        }

    }
}

@Composable
fun RoundedButton(
    label: String = "Reading",
    radius: Int = 29,
    onPress: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStartPercent = radius,
                bottomEndPercent = radius
            )
        ),
        shadowElevation = 6.dp,
        color = Color(0xFF92CBDF)
    ) {
        Column(
            modifier = Modifier
                .width(90.dp)
                .heightIn(40.dp)
                .clickable {
                    onPress.invoke()
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                fontSize = 15.sp,
            )
        }
    }

}

@Composable
fun ReadingRightNowArea(books: List<MBook>, navController: NavController) {
    ListCard()
}

