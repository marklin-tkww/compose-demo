package com.example.myapplication.composeAndview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.composeAndview.usecase.TestUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ComposeUiViewModel(
    private val testUseCase: TestUseCase
) : ViewModel() {

    private val _number = MutableLiveData<Int>().apply { value = 0 }
    val number: LiveData<Int> = _number
    fun updateNumber(number: Int) {
        _number.value = number + 1
    }

    private val _number1 = MutableLiveData<Int>().apply { value = 0 }
    val number1: LiveData<Int> = _number1
    fun updateNumber1(number1: Int) {
        _number1.value = number1 + 1
    }

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> = _responseMessage

    fun testRequestBaiDu() {
        testUseCase.testRequestBaiDu().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _responseMessage.value = it
            }
    }

}