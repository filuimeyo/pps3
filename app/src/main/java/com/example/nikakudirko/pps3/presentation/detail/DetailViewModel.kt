package com.example.nikakudirko.pps3.presentation.detail

import androidx.compose.runtime.MutableState
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

sealed class DetailIntent {
    data class Initialize(val articleId: Long) : DetailIntent()
    data class ChangeTitle(val title: String) : DetailIntent()
    data class ChangeContent(val content: String) : DetailIntent()
    object ToggleBookmark : DetailIntent()
    object SaveArticle : DetailIntent()
    //object NavigateUp : DetailIntent()
}

/*
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

}*/

data class DetailViewState(
    val id: Long = 0,
    val title: String = "",
    val content: String = "",
    val isBookmarked: Boolean = false,
    val createdDate: Date = Date(),
    val isUpdatingArticle: Boolean = false,
)

class DetailViewModel @AssistedInject constructor(
    private val addUseCase: AddUseCase,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    @Assisted private val articleId: Long
) : ViewModel() {
    private val _state = mutableStateOf(DetailViewState())
    val state: MutableState<DetailViewState> = _state

    init {
        println("artivle idddddddddddddddddddddddddddddddddddddddd ${articleId}")
        processIntent(DetailIntent.Initialize(articleId))
    }

    val isFormNotBlank: Boolean
        get() = state.value.title.isNotEmpty() &&
                state.value.content.isNotEmpty()

    fun processIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.Initialize -> {
                val isUpdatingArticle = intent.articleId != -1L
                _state.value = state.value.copy(isUpdatingArticle = isUpdatingArticle)

                if (isUpdatingArticle) {
                    getArticleById(intent.articleId)
                }
            }
            is DetailIntent.ChangeTitle -> {
                _state.value = state.value.copy(title = intent.title)
            }
            is DetailIntent.ChangeContent -> {
                _state.value = state.value.copy(content = intent.content)
            }
            is DetailIntent.ToggleBookmark -> {
                _state.value = state.value.copy(isBookmarked = !state.value.isBookmarked)
            }
            is DetailIntent.SaveArticle -> {
                addOrUpdateArticle()
            }
            /*is DetailIntent.NavigateUp -> {

                // Обработка навигации назад
                // ...
            }*/
        }
    }

    private fun getArticleById(articleId: Long) = viewModelScope.launch {
        getArticleByIdUseCase(articleId).collectLatest { article ->
            _state.value = state.value.copy(
                title = article.title,
                content = article.content,
                isBookmarked = article.isBookMarked
            )
        }
    }

    private fun addOrUpdateArticle() = viewModelScope.launch {


        val article =  if(articleId == -1L) Article(

            title = state.value.title,
            content = state.value.content,
            createdDate = Date(),
            isBookMarked = state.value.isBookmarked
        ) else Article(
            id = articleId,
            title = state.value.title,
            content = state.value.content,
            createdDate = Date(),
            isBookMarked = state.value.isBookmarked
        )

        addUseCase(article)
    }
}






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
