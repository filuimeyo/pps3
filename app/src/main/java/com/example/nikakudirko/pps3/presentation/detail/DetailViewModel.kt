package com.example.nikakudirko.pps3.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nikakudirko.pps3.data.local.model.Article
import com.example.nikakudirko.pps3.domain.use_cases.AddUseCase
import com.example.nikakudirko.pps3.domain.use_cases.GetArticleByIdUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date


class DetailViewModel @AssistedInject constructor(
    private val addUseCase: AddUseCase,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    @Assisted private val articleId: Long

) : ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    val isFormNotBlank: Boolean
        get() = state.title.isNotEmpty() &&
                state.content.isNotEmpty()


    private val article: Article
        get() = state.run {
            Article(
                id = id,
                title =title,
                content = content,
                createdDate = createdDate,
                isBookMarked = isBookmarked
            )
        }

    init{
        initialize()
    }


    private fun initialize() {
        val isUpdatingArticle = articleId != -1L
        state = state.copy(isUpdatingArticle = isUpdatingArticle)

        if (isUpdatingArticle) {
            getArticleById()
        }
    }


    private fun getArticleById() = viewModelScope.launch {
        getArticleByIdUseCase(articleId).collectLatest { article ->
            state = state.copy(
                id = article.id,
                title = article.title,
                content = article.content,
                isBookmarked = article.isBookMarked,
                createdDate = article.createdDate
            )
        }
    }



    fun onTitleChange(title: String) {
        state = state.copy(title = title)
    }

    fun onContentChange(content: String) {
        state = state.copy(content = content)
    }

    fun onBookmarkChange(isBookmarked: Boolean) {
        state = state.copy(isBookmarked = isBookmarked)
    }

    fun addOrUpdateArticle() = viewModelScope.launch {
        addUseCase(article = article)
    }

}


data class DetailState(
    val id: Long = 0,
    val title: String = "",
    val content: String = "",
    val isBookmarked: Boolean = false,
    val createdDate: Date = Date(),
    val isUpdatingArticle: Boolean = false,
)

@Suppress("UNCHECKED_CAST")
class DetailedViewModelFactory(
    private val articleId: Long,
    private val assistedFactory: DetailAssistedFactory
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(articleId) as T
    }
}

@AssistedFactory
interface DetailAssistedFactory {
    fun create(articleId: Long): DetailViewModel
}