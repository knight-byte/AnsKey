package com.knightbyte.answers.network.response

import com.google.gson.annotations.SerializedName

data class CredDataResponse(
    @SerializedName("client_id"     ) var clientId     : String,
    @SerializedName("client_secret" ) var clientSecret : String,
    @SerializedName("grant_type"    ) var grantType    : String,
    @SerializedName("refresh_token" ) var refreshToken : String,
    @SerializedName("drive_id"      ) var driveId      : String,
)
