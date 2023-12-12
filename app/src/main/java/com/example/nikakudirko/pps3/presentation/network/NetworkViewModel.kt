package com.example.nikakudirko.pps3.presentation.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nikakudirko.pps3.network.NetworkArticlesRepository
import com.example.nikakudirko.pps3.network.Resourse
import com.example.nikakudirko.pps3.network.models.NetworkArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NetworkViewModel @Inject constructor(
    private val repository: NetworkArticlesRepository
): ViewModel(){
    private val _networkArticles = MutableStateFlow<Resourse<List<NetworkArticle>>?>(null)
    val networkArticles : StateFlow<Resourse<List<NetworkArticle>>?> = _networkArticles

    init{
        viewModelScope.launch {
            _networkArticles.value = Resourse.Loading
            _networkArticles.value =repository.getNetworkArticles()
        }
    }

}