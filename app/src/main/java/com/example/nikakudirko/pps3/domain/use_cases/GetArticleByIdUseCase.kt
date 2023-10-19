package com.example.nikakudirko.pps3.domain.use_cases

import com.example.nikakudirko.pps3.domain.repository.Repository
import javax.inject.Inject

class GetArticleByIdUseCase @Inject constructor(
    private val repository: Repository
){
    operator fun invoke(id: Long) = repository.getArticleById(id)
}