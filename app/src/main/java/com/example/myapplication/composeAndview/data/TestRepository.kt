package com.example.myapplication.composeAndview.data

import com.example.myapplication.composeAndview.source.TestRemoteService
import io.reactivex.Observable

class TestRepository(
    private val testRemoteService: TestRemoteService
) {

    fun testRequestBaiDu(): Observable<String> =
        testRemoteService.testRequestBaiDu()

    companion object {

        @Volatile
        private var INSTANCE: TestRepository? = null

        fun getInstance() = INSTANCE ?: synchronized(TestRepository::class.java) {
            INSTANCE ?: TestRepository(
                TestRemoteService()
            ).also {
                INSTANCE = it
            }
        }
    }
}