package com.example.nikakudirko.pps3.domain.use_cases

import com.example.nikakudirko.pps3.domain.repository.Repository
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Long) = repository.delete(id)
}