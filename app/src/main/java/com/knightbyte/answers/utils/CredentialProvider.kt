package com.knightbyte.answers.utils

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.appcheck.FirebaseAppCheck
import com.knightbyte.answers.BuildConfig
import com.knightbyte.answers.network.response.CredDataResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

class CredentialProvider {

    companion object {
    interface CredentialProviderInterface {
        @GET("credentials")
        fun credentials(
            @Header("X-Firebase-AppCheck") appCheckToken: String
        ): Call<CredDataResponse>
    }

    private val credentialProviderService = Retrofit.Builder()
        .baseUrl(BuildConfig.CUSTOM_BACKEND_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .build()
        )
        .build()
        .create(CredentialProviderInterface::class.java)
        val firebaseStatus: MutableState<Resource<Boolean>> = mutableStateOf(Resource.Empty())


        fun getCredentials() {
            val credTag = "-- CRED --"
            Log.d(credTag, "Start")
            firebaseStatus.value= Resource.Loading()
            FirebaseAppCheck.getInstance()
                .getToken(false)
                .addOnSuccessListener { tokenResponse ->
                    val appCheckToken = tokenResponse.token

                    Log.d(credTag, "Success token Gen : $appCheckToken")
                    credentialProviderService.credentials(appCheckToken)
                        .enqueue(object : Callback<CredDataResponse> {
                            override fun onResponse(
                                call: Call<CredDataResponse>,
                                response: Response<CredDataResponse>
                            ) {
                                Constants.DriveCredential = response.body()
                                if (Constants.DriveCredential != null) {
                                    Log.d(
                                        credTag,
                                        "Success Cred Gen : ${Constants.DriveCredential}"
                                    )
                                    firebaseStatus.value = Resource.Success(true)
                                } else {
                                    Log.d(
                                        credTag,
                                        "FAIL Cred Gen Empty : ${Constants.DriveCredential}"
                                    )
                                    firebaseStatus.value = Resource.Error("Error : Empty Response")
                                }
                            }

                            override fun onFailure(call: Call<CredDataResponse>, t: Throwable) {
                                Log.d(credTag, "FAIL Cred Gen : $t")

                                firebaseStatus.value = Resource.Error("Error : Fail to fetch Data")
                            }

                        })
                }
                .addOnFailureListener { exception ->
                    Log.d(credTag, "FAIL token Gen : $exception")
                    firebaseStatus.value =
                        Resource.Error("Error : Fail to fetch Token : $exception")
                }
        }
    }
}