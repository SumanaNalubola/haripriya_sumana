package com.dbs.careerguidance.data.api

import com.dbs.careerguidance.model.ServerResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface EducationApi {

    @FormUrlEncoded
    @POST("login.php")
    fun checkUser(
        @Field("uname") uname: String,
        @Field("pass") pass: String
    ): Call<ServerResponse>

    @FormUrlEncoded
    @POST("register.php")
    fun registerUser(
        @Field("uname") uname: String,
        @Field("email") email: String,
        @Field("pass") pass: String
    ): Call<ServerResponse>

}
