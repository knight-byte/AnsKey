package com.knightbyte.answers.repository

import android.util.Log
import com.knightbyte.answers.network.model.TokenEntity
import com.knightbyte.answers.network.response.CredDataResponse
import com.knightbyte.answers.network.services.TokenService
import com.knightbyte.answers.utils.CUSTOM_CHECK_DEBUG_LOG
import com.knightbyte.answers.utils.Constants
import com.knightbyte.answers.utils.CredentialProvider
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepository
@Inject
constructor(
    private val tokenService: TokenService
) : AuthRepoInterface {
    override suspend fun getToken(): String {

        val credDataResponse = Constants.DriveCredential
        Log.d(CUSTOM_CHECK_DEBUG_LOG,"Token req Started $credDataResponse")
        if(credDataResponse!=null) {
            val tokenResponse = tokenService.generateToken(
                TokenEntity(
                    clientId = credDataResponse.clientId,
                    clientSecret = credDataResponse.clientSecret,
                    grantType = credDataResponse.grantType,
                    refreshToken = credDataResponse.refreshToken
                )
            )
            Constants.Token = "${tokenResponse.tokenType} ${tokenResponse.accessToken}"
        }else {
            Constants.Token = ""
        }
        Log.d(CUSTOM_CHECK_DEBUG_LOG,"TOKEN generated : ${Constants.Token}")

        return Constants.Token!!
    }
}
