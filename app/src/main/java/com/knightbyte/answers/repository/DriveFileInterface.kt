package com.knightbyte.answers.repository

import com.knightbyte.answers.domain.model.TestFile
import com.knightbyte.answers.network.model.ListDriveEntity
import com.knightbyte.answers.utils.Resource

interface DriveFilesInterface {

    suspend fun listFiles(): Resource<List<TestFile>>
}