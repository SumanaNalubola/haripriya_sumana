package com.dbs.careerguidance.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("uname")
    var uname: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("pass")
    var password: String
)