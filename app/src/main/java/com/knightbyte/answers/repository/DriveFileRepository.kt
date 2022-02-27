package com.knightbyte.answers.repository

import com.knightbyte.answers.BuildConfig
import com.knightbyte.answers.domain.model.TestFile
import com.knightbyte.answers.network.model.ListDriveDtoMapper
import com.knightbyte.answers.network.model.ListDriveEntity
import com.knightbyte.answers.network.services.DriveService
import com.knightbyte.answers.utils.Constants
import com.knightbyte.answers.utils.Resource
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DriveFileRepository @Inject constructor(
    private val driveService: DriveService,
    private val authRepository: AuthRepository,
    private val listDriveDtoMapper: ListDriveDtoMapper
) : DriveFilesInterface {
    override suspend fun listFiles(): Resource<List<TestFile>> {
        return try {
            val token = Constants.Token ?: authRepository.getToken()

            val driveID = Constants.DriveCredential!!.driveId
            val listFileResponse = driveService.listFiles(
                token = token,
                driveId = driveID,
                q = "mimeType='application/vnd.google-apps.folder'"
            )
            val testList: MutableList<ListDriveEntity> = mutableListOf()

            listFileResponse.files.forEach { folder ->
                val folderId = folder.id
                val query = "'${folderId}' in parents and mimeType='application/pdf'"
                val singleResponse = driveService.listFiles(
                    token = token,
                    driveId = driveID,
                    q = query
                )
                singleResponse.files.forEach {
                    testList.add(it)
                }
            }

            return Resource.Success(listDriveDtoMapper.mapToDomainList(testList))
        } catch (t: Throwable) {
            val error = when (t) {
                is IOException -> "Network Failure"
                is retrofit2.HttpException -> "Invalid Token Error"

                else -> "Conversion Error : $t"
            }
            Resource.Error(error)
        }
    }
}