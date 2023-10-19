package com.example.nikakudirko.pps3.domain.use_cases

import com.example.nikakudirko.pps3.data.local.model.Article
import com.example.nikakudirko.pps3.domain.repository.Repository
import javax.inject.Inject

class AddUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(article: Article) = repository.insert(article)
}

//operator  = AddUseCase() tipo kak function