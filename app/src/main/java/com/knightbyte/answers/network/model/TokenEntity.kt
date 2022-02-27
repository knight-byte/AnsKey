package com.knightbyte.answers.network.model

import com.google.gson.annotations.SerializedName
import com.knightbyte.answers.BuildConfig

data class TokenEntity(
    @SerializedName("client_id")
    val clientId: String ,

    @SerializedName("client_secret")
    val clientSecret: String,

    @SerializedName("grant_type")
    val grantType: String,

    @SerializedName("refresh_token")
    val refreshToken: String,
)
