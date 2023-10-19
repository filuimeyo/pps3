package com.example.nikakudirko.pps3.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikakudirko.pps3.common.ScreenViewState
import com.example.nikakudirko.pps3.data.local.model.Article
import com.example.nikakudirko.pps3.domain.use_cases.DeleteArticleUseCase
import com.example.nikakudirko.pps3.domain.use_cases.FilteredBookMarkedArticles
import com.example.nikakudirko.pps3.domain.use_cases.UpdateArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val updateArticleUseCase: UpdateArticleUseCase,
    private val filteredBookMarkedArticles: FilteredBookMarkedArticles,
    private val deleteArticleUseCase: DeleteArticleUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<BookMarkState> = MutableStateFlow(BookMarkState())
    val state: StateFlow<BookMarkState> = _state.asStateFlow()


    private fun getBookMarkedArticles() {
        filteredBookMarkedArticles().onEach {
            _state.value = BookMarkState(
                articles = ScreenViewState.Success(it)
            )
        }
            .catch {
                _state.value = BookMarkState(articles = ScreenViewState.Error(it.message))
            }
            .launchIn(viewModelScope)

    }


    fun onBookMarkedChanged(article: Article) {
        viewModelScope.launch {
            updateArticleUseCase(
                article.copy(
                    isBookMarked = !article.isBookMarked
                )
            )
        }
    }

    fun deleteArticle(articleId: Long) {
        viewModelScope.launch {
            deleteArticleUseCase(articleId)
        }

    }
}

data class BookMarkState(
    val articles: ScreenViewState<List<Article>> = ScreenViewState.Loading
)