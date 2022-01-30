package com.knightbyte.answers.network.services

import com.knightbyte.answers.network.model.TokenEntity
import com.knightbyte.answers.network.response.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {

    @POST("token")
    suspend fun generateToken(
        @Body request: TokenEntity
    ): TokenResponse

}