package com.example.noteapp.domain.usecase

import com.example.noteapp.data.api.ArticlesItem
import com.example.noteapp.data.api.BaseResult
import com.example.noteapp.domain.repo.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class NewsUseCase(
    val getHeadline: GetHeadline
)

class GetHeadline(private val repo: NewsRepository) {

    operator fun invoke(
        country: String,
        page: Int,
        pageSize: Int
    ): Flow<BaseResult<List<ArticlesItem?>?>> {
        return flow {
            emit(BaseResult.Loading())
            val result = repo.getNewsHeadline(country, page, pageSize)
            emit(result)
        }
    }
}
