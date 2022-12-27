package com.example.myapplication.composeAndview.source

import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*


class TestRemoteService {
    private lateinit var retrofit: Retrofit
    private lateinit var testService: TestService

    init {
        initAPIServiceRetrofit()
    }

    private fun initAPIServiceRetrofit() {
        retrofit = createRetrofit()
        initAPIService()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://www.baidu.com")
            .build()
    }

    private fun initAPIService() {
        testService = retrofit.create(TestService::class.java)
    }

    fun testRequestBaiDu(): Observable<String> = testService.testRequestBaiDu()

    private interface TestService {
        @GET("/")
        fun testRequestBaiDu(): Observable<String>
    }
}