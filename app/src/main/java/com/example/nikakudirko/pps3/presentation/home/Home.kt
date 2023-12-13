
package com.example.nikakudirko.pps3.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nikakudirko.pps3.common.ScreenViewState
import com.example.nikakudirko.pps3.data.local.model.Article
import java.util.Date

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    onBookMarkChange: (article: Article) -> Unit,
    onDeleteArticle: (Long) -> Unit,
    onArticleClicked: (Long) -> Unit
) {
    when (state.articles) {
        is ScreenViewState.Loading -> {
            CircularProgressIndicator()
        }

        is ScreenViewState.Success -> {
            val articles = state.articles.data
            HomeDetail(
                articles = articles ,
                modifier = modifier,
                onBookMarkChange = onBookMarkChange,
                onDeleteArticle = onDeleteArticle,
                onArticleClicked = onArticleClicked
            )
        }

        is ScreenViewState.Error->{
            Text(
                text = state.articles.message ?: "Unknown Error",
                color = MaterialTheme.colorScheme.error
            )
        }
    }

}


@Composable
private fun HomeDetail(
    articles: List<Article>,
    modifier: Modifier,
    onBookMarkChange: (article: Article) -> Unit,
    onDeleteArticle: (Long) -> Unit,
    onArticleClicked: (Long) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier
    ) {
        itemsIndexed(articles) { index, article ->

            ArticleCard(
                index = index,
                article = article,
                onBookMarkChange = onBookMarkChange,
                onDeleteArticle = onDeleteArticle,
                onArticleClicked = onArticleClicked
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleCard(
    index: Int,
    article: Article,
    onBookMarkChange: (article: Article) -> Unit,
    onDeleteArticle: (Long) -> Unit,
    onArticleClicked: (Long) -> Unit
) {

    val isEvenIndex = index % 2 == 0
    val shape = when {
        isEvenIndex -> {
            RoundedCornerShape(
                topStart = 50f,
                bottomEnd = 50f
            )
        }

        else -> {
            RoundedCornerShape(
                topEnd = 50f,
                bottomStart = 50f
            )
        }
    }

    val icon = if (article.isBookMarked) Icons.Default.BookmarkRemove
    else Icons.Outlined.BookmarkAdd


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = shape,
        onClick = { onArticleClicked(article.id) }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Text(
                text = article.title + " " + article.id,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = article.content,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = { onDeleteArticle(article.id) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }

                IconButton(onClick = { onBookMarkChange(article) }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )

                }
            }


        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevHome(){
    HomeScreen(
        state = HomeState(
            articles = ScreenViewState.Success(articles)
        ) ,
        onBookMarkChange = {},
        onDeleteArticle = {},
        onArticleClicked = {}
    )
}

val placeHolderText =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas porttitor nunc vel metus mollis suscipit. Phasellus nec eros id ex aliquam scelerisque. Phasellus quis feugiat eros. Nam sodales ante ac lorem convallis tempus. Sed lacinia consequat diam at ultrices. Nullam lacinia dignissim aliquam. Proin sit amet quam efficitur, euismod nunc eu, aliquam orci. Ut mattis orci a purus ultricies sodales. Pellentesque odio quam, aliquet nec accumsan et, pharetra et lacus. Pellentesque faucibus, dolor quis iaculis fringilla, ligula nisl imperdiet massa, vel volutpat velit elit ac magna. Interdum et malesuada fames ac ante ipsum primis in faucibus. Vivamus pharetra dolor nec magna condimentum volutpat. "

val articles = listOf(
    Article(
        title = "Room Database",
        content = placeHolderText + placeHolderText,
        createdDate = Date()
    ),
    Article(
        title = "JetPack Compose",
        content = "Testing",
        createdDate = Date(),
        isBookMarked = true,

        ),
    Article(
        title = "Room Database",
        content = placeHolderText + placeHolderText,
        createdDate = Date()
    ),
    Article(
        title = "JetPack Compose",
        content = placeHolderText,
        createdDate = Date(),
        isBookMarked = true,

        ),
    Article(
        title = "Room Database",
        content = placeHolderText,
        createdDate = Date()
    ),
    Article(
        title = "JetPack Compose",
        content = placeHolderText + placeHolderText,
        createdDate = Date(),
        isBookMarked = true,
    ),
)


