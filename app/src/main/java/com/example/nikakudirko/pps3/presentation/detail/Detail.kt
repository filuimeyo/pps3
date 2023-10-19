package com.example.nikakudirko.pps3.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.nikakudirko.pps3.data.local.model.Article

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    articleId: Long,
    assistedFactory: DetailAssistedFactory,
    navigateUp: ()-> Unit,
){

}

@Composable
private fun DetailScreen(
    modifier: Modifier,
    isUpdatingArticle: Boolean,
    title: String,
    content: String,
    isBookmark: Boolean,
    isFormNotBlank: Boolean,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onBtnClick: () -> Unit,
    onNavigate: ()-> Unit
){

}