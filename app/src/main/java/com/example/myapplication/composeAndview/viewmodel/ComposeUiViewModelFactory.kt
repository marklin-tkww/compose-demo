package com.example.myapplication.composeAndview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.composeAndview.usecase.TestUseCase

class ComposeUiViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private val testUseCase = TestUseCase.newInstance()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(ComposeUiViewModel::class.java) ->
                    ComposeUiViewModel(testUseCase)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}