package com.dbs.careerguidance.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private var BASE_URL ="https://wvento.com/project1/"

    val api : EducationApi by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(EducationApi::class.java)
    }

}