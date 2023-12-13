package com.example.nikakudirko.pps3.presentation.mvi

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.example.nikakudirko.pps3.domain.use_cases.AddUseCase
import com.example.nikakudirko.pps3.domain.use_cases.GetArticleByIdUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

sealed class Intent {
    data class ButtonClick(val id: String) : Intent()
}

class MVIViewModel @AssistedInject constructor(
    @Assisted private val articleId: Long

) : ViewModel() {

}
@Composable
fun MVIScreen(
    articleId: Long,
){

}
