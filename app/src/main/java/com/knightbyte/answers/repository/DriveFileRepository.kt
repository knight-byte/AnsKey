package com.knightbyte.answers.repository

import com.knightbyte.answers.BuildConfig
import com.knightbyte.answers.domain.model.TestFile
import com.knightbyte.answers.network.model.ListDriveDtoMapper
import com.knightbyte.answers.network.model.ListDriveEntity
import com.knightbyte.answers.network.services.DriveService
import com.knightbyte.answers.utils.Resource
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
            val token = authRepository.getToken()
            val listFileResponse = driveService.listFiles(
                token = token,
                driveId = BuildConfig.DRIVE_ID,
                q = "mimeType='application/vnd.google-apps.folder'"
            )
            val testList: MutableList<ListDriveEntity> = mutableListOf()
            listFileResponse.files.forEach { folder ->
                val folderId = folder.id
                val query = "'${folderId}' in parents and mimeType='application/pdf'"
                val singleResponse = driveService.listFiles(
                    token = token,
                    driveId = BuildConfig.DRIVE_ID,
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
                else -> "Conversion Error"
            }
            Resource.Error(error)
        }
    }
}