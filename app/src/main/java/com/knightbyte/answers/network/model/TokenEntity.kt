package com.knightbyte.answers.network.model

import com.google.gson.annotations.SerializedName
import com.knightbyte.answers.BuildConfig

data class TokenEntity(
    @SerializedName("client_id")
    val clientId: String = BuildConfig.CLIENT_ID,

    @SerializedName("client_secret")
    val clientSecret: String = BuildConfig.CLIENT_SECRET,

    @SerializedName("grant_type")
    val grantType: String = BuildConfig.GRANT_TYPE,

    @SerializedName("refresh_token")
    val refreshToken: String = BuildConfig.REFRESH_TOKEN,
)
