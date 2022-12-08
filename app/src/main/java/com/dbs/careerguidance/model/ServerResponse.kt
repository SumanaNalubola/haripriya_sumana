package com.dbs.careerguidance.model

import com.google.gson.annotations.SerializedName

data class ServerResponse(
    @SerializedName("success")
    var status: Int,
    @SerializedName("message")
    var message: String
)
