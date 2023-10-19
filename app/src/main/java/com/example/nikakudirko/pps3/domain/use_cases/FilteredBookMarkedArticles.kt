package com.example.nikakudirko.pps3.domain.use_cases

import com.example.nikakudirko.pps3.data.local.model.Article
import com.example.nikakudirko.pps3.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilteredBookMarkedArticles @Inject constructor(
    private val repository: Repository
){

    operator fun invoke(): Flow<List<Article>> {
        return repository.getBookMarkedArticles()
    }
}