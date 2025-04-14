package com.example.noteapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.api.ArticlesItem
import com.example.noteapp.data.api.BaseResult
import com.example.noteapp.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewmodel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {

    private val _headlineState =
        MutableStateFlow<BaseResult<List<ArticlesItem?>?>>(BaseResult.Idle())
    val headlineState: StateFlow<BaseResult<List<ArticlesItem?>?>> = _headlineState

    fun fetchNewsHeadline(country: String, page: Int, pageSize: Int) {
        viewModelScope.launch {
            newsUseCase.getHeadline(country, page, pageSize)
                .collect { result ->
                    _headlineState.value = result
                }
        }
    }

}