package com.example.noteapp.domain.repo

import com.example.noteapp.data.api.ArticlesItem
import com.example.noteapp.data.api.BaseResult

interface NewsRepository {

    suspend fun getNewsHeadline(
        country: String,
        page: Int,
        pageSize: Int
    ): BaseResult<List<ArticlesItem?>?>

}