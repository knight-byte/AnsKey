package com.knightbyte.answers.network.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("expires_in")
    val expiresIn: Int,

    @SerializedName("scope")
    val scope: String,

    @SerializedName("token_type")
    val tokenType: String
)