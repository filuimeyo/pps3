package com.example.nikakudirko.pps3.presentation.bookmark

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nikakudirko.pps3.common.ScreenViewState
import com.example.nikakudirko.pps3.data.local.model.Article
import com.example.nikakudirko.pps3.presentation.home.ArticleCard
import com.example.nikakudirko.pps3.presentation.home.HomeState
import com.example.nikakudirko.pps3.presentation.home.articles

@Composable
fun BookmarkScreen(
    state: BookMarkState,
    modifier: Modifier = Modifier,
    onBookmarkChange: (article: Article) -> Unit,
    onDelete: (id: Long) -> Unit,
    onArticleClicked: (Long) -> Unit
) {

    when (state.articles) {
        is ScreenViewState.Loading -> {
            CircularProgressIndicator()
        }

        is ScreenViewState.Success -> {
            val articles = state.articles.data
            LazyColumn(
                contentPadding = PaddingValues(4 .dp),
                modifier = modifier
            ) {
                itemsIndexed(articles) { index: Int, article: Article ->
                    ArticleCard(
                        index = index,
                        article = article,
                        modifier = modifier,
                        onBookMarkChange = onBookmarkChange,
                        onDeleteArticle = onDelete,
                        onArticleClicked = onArticleClicked
                    )
                }
            }
        }

        is ScreenViewState.Error ->{
            Text(
                text = state.articles.message ?: "Unknown Error",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevBookmark(){
    BookmarkScreen(
        state = BookMarkState(
            articles = ScreenViewState.Success(articles)
        ),
        onBookmarkChange = {},
        onDelete = {},
        onArticleClicked = {}
    )
}