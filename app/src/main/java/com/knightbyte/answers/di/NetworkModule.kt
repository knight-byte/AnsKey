package com.knightbyte.answers.di

import com.knightbyte.answers.network.services.DriveService
import com.knightbyte.answers.network.services.TokenService
import com.knightbyte.answers.utils.DRIVE_API_BASE_URL
import com.knightbyte.answers.utils.DRIVE_TOKEN_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideDriveService(): DriveService{
        return Retrofit.Builder()
            .baseUrl(DRIVE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .build()
            )
            .build()
            .create(DriveService::class.java)
    }

    @Singleton
    @Provides
    fun provideTokenService(): TokenService{
        return Retrofit.Builder()
            .baseUrl(DRIVE_TOKEN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .build()
            )
            .build()
            .create(TokenService::class.java)
    }
}