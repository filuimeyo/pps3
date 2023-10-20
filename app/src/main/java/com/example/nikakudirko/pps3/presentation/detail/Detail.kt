package com.example.nikakudirko.pps3.presentation.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    articleId: Long,
    assistedFactory: DetailAssistedFactory,
    navigateUp: () -> Unit,
) {

    val viewModel = viewModel(
        modelClass = DetailViewModel::class.java,
        factory = DetailedViewModelFactory(
            articleId = articleId,
            assistedFactory = assistedFactory
        )
    )

    val state = viewModel.state
    DetailScreen(
        modifier = modifier,
        isUpdatingArticle = state.isUpdatingArticle,
        title = state.title,
        content = state.content,
        isBookmark = state.isBookmarked,
        onBookmarkChange = viewModel::onBookmarkChange,
        isFormNotBlank = viewModel.isFormNotBlank,
        onTitleChange = viewModel::onTitleChange,
        onContentChange = viewModel::onContentChange,
        onBtnClick = {
            viewModel.addOrUpdateArticle()
            navigateUp()
        },
        onNavigate = navigateUp
    )
}

@Composable
private fun DetailScreen(
    modifier: Modifier,
    isUpdatingArticle: Boolean,
    title: String,
    content: String,
    isBookmark: Boolean,
    onBookmarkChange: (Boolean) -> Unit,
    isFormNotBlank: Boolean,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onBtnClick: () -> Unit,
    onNavigate: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        TopSection(
            title = title,
            isBookmark = isBookmark,
            onBookmarkChange = onBookmarkChange,
            onTitleChange = onTitleChange,
            onNavigate = onNavigate
        )

        Spacer(modifier = Modifier.size(12.dp))

        AnimatedVisibility(isFormNotBlank) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onBtnClick) {
                    val icon = if (isUpdatingArticle) Icons.Default.Update
                    else Icons.Default.Check

                    Icon(imageVector = icon, contentDescription = null)
                }
            }
        }

        Spacer(modifier = Modifier.size(12.dp))

        ArticlesTextField(
            modifier = Modifier.weight(1f),
            value = content,
            label = "Content",
            onValueChange = onContentChange
        )


    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    title: String,
    isBookmark: Boolean,
    onBookmarkChange: (Boolean) -> Unit,
    onTitleChange: (String) -> Unit,
    onNavigate: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = onNavigate) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )

        }
        ArticlesTextField(
            modifier = Modifier.weight(1f),
            value = title,
            label = "Title",
            labelAlign = TextAlign.Center,
            onValueChange = onTitleChange
        )

        IconButton(onClick = { onBookmarkChange(!isBookmark) }) {
            val icon = if (isBookmark) Icons.Default.BookmarkRemove
            else Icons.Default.BookmarkAdd
            Icon(
                imageVector = icon,
                contentDescription = null
            )

        }
    }

}

@Composable
private fun ArticlesTextField(
    modifier: Modifier,
    value: String,
    label: String,
    labelAlign: TextAlign? = null,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            disabledPlaceholderColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = "Insert $label",
                textAlign = labelAlign,
                modifier = modifier.fillMaxWidth()
            )
        }
    )

}