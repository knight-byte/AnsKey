package com.knightbyte.answers.di

import com.knightbyte.answers.network.model.ListDriveDtoMapper
import com.knightbyte.answers.network.services.DriveService
import com.knightbyte.answers.utils.DRIVE_API_BASE_URL
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
object DomainModule {
    @Singleton
    @Provides
    fun provideListFileDtoMapper(): ListDriveDtoMapper {
        return ListDriveDtoMapper()
    }
}