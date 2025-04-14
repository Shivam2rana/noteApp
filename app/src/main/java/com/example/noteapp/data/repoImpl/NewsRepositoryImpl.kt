package com.example.noteapp.data.repoImpl

import com.example.noteapp.data.api.ApiService
import com.example.noteapp.data.api.ArticlesItem
import com.example.noteapp.data.api.BaseResult
import com.example.noteapp.domain.repo.NewsRepository
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(val apiService: ApiService) : NewsRepository {

    override suspend fun getNewsHeadline(
        country: String,
        page: Int,
        pageSize: Int
    ): BaseResult<List<ArticlesItem?>?> {
        return try {
            val response = apiService.getHeadLines(country, page, pageSize)
            if (response.isSuccessful) {
                response.body()?.let {

                    BaseResult.Success(
                        data = it.articles,
                        statusCode = response.code(),
                        message = response.message()
                    )
                } ?: run {
                    BaseResult.Error(
                        statusCode = response.code(),
                        message = response.message()
                    )
                }
            } else {
                BaseResult.Error(
                    statusCode = response.code(),
                    message = response.message()
                )
            }
        } catch (e: Exception) {

            BaseResult.Error(
                statusCode = 1010,
                message = "Cause: ${e.cause}\nMessage: ${e.message}"
            )
        }
    }
}
