package com.dong.reader.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import com.dong.reader.components.FABContent
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
            FABContent {}
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
                Spacer(modifier = Modifier.fillMaxSize(0.7f))
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
        }
    }
}

@SuppressLint("LocalContextResourcesRead")
@Preview
@Composable
fun ListCard(
    book: MBook = MBook(
        "id01", "Book title", "Book author", "hello",
        "image url", listOf("book categories"), "10/10/2025", 1
    ), onPressDetails: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val resources = context.resources
    val displayMetrics = resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val screenHeight = displayMetrics.heightPixels / displayMetrics.density
    val spacing = 10.dp

    Card(
        modifier = Modifier
            .clickable {
                onPressDetails.invoke(book.title)
            }
            .padding(16.dp)
            .height(242.dp)
            .width(202.dp),
        shape = RoundedCornerShape(29.dp),
    ) {
        Column(
            modifier = Modifier
                .width(screenWidth.dp - (spacing * 2))
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(book.photoUrl),
                    contentDescription = "book image",
                    modifier = Modifier
                        .height(140.dp)
                        .width(100.dp)
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.width(50.dp))
                Column {  }

            }
        }
    }
}


@Composable
fun ReadingRightNowArea(books: List<MBook>, navController: NavHostController) {

}

