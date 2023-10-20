package com.example.nikakudirko.pps3.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikakudirko.pps3.common.ScreenViewState
import com.example.nikakudirko.pps3.data.local.model.Article
import com.example.nikakudirko.pps3.domain.use_cases.DeleteArticleUseCase
import com.example.nikakudirko.pps3.domain.use_cases.GetAllArticlesUseCase
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
class HomeViewModel @Inject constructor(
    private val getAllArticlesUseCase: GetAllArticlesUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase,
    private val updateArticleUseCase: UpdateArticleUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        getAllArticles()
    }

    private fun getAllArticles(){
        getAllArticlesUseCase()
            .onEach {
                _state.value = HomeState(articles = ScreenViewState.Success(it))
            }
            .catch{
                _state.value = HomeState(articles = ScreenViewState.Error(it.message))
            }
            .launchIn(viewModelScope)
    }


    fun deleteArticle(articleId: Long) = viewModelScope.launch {
        deleteArticleUseCase(articleId)
    }

    fun onBookMarkChange(article: Article) = viewModelScope.launch {
        updateArticleUseCase(article.copy(isBookMarked = !article.isBookMarked))
    }


}

data class HomeState(
    val articles: ScreenViewState<List<Article>> = ScreenViewState.Loading,
)