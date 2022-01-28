package com.knightbyte.answers.network.services

import com.knightbyte.answers.network.response.ListDriveResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface DriveService {

    // Get List of files and folders from drive
    @GET("files")
    suspend fun listFiles(
        @Header("Authorization") token: String,
        @Query("corpora") corpora: String = "drive",
        @Query("driveId") driveId: String,
        @Query("includeItemsFromAllDrives") includeItemsFromAllDrives: Boolean = true,
        @Query("supportsAllDrives") supportsAllDrives: Boolean = true,
        @Query("q") q: String? = null
    ): ListDriveResponse

}