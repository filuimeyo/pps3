package com.example.nikakudirko.pps3.presentation.network

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nikakudirko.pps3.network.Resourse
import com.example.nikakudirko.pps3.network.models.NetworkArticle


@Composable
fun NetworkScreen(
    viewModel: NetworkViewModel
) {
    val contex = LocalContext.current
    val articles = viewModel.networkArticles.collectAsState()

    articles.value?.let{
        when(it){
            is Resourse.Failure -> {
               Toast.makeText(contex, it.exception.message!!, Toast.LENGTH_SHORT)
            }
            Resourse.Loading -> {
                Text("loading")
            }
            is Resourse.Success -> {
                NetworkArticlesList(it.result)
            }
        }
    }
}

@Composable
fun NetworkArticlesList(articles: List<NetworkArticle>) {
    LazyColumn{
        items(articles){ item->
            Text(text = item.author)
        }
    }
}


