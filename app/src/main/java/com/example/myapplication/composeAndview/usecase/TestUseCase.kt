package com.example.myapplication.composeAndview.usecase

import com.example.myapplication.composeAndview.data.TestRepository
import io.reactivex.Observable

class TestUseCase(
    private val testRepository: TestRepository
) {
    fun testRequestBaiDu(
    ): Observable<String> =
        testRepository.testRequestBaiDu()

    companion object {

        @JvmStatic
        fun newInstance(): TestUseCase =
            TestUseCase(
                TestRepository.getInstance()
            )
    }
}