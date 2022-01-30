package com.knightbyte.answers.repository

import com.knightbyte.answers.network.model.TokenEntity
import com.knightbyte.answers.network.services.TokenService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepository
@Inject
constructor(
    private val tokenService: TokenService
) : AuthRepoInterface {
    override suspend fun getToken(): String {
        val tokenResponse = tokenService.generateToken(TokenEntity())
        return "${tokenResponse.tokenType} ${tokenResponse.accessToken}"
    }
}
