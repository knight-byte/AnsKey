package com.knightbyte.answers.network.model

import com.knightbyte.answers.domain.model.TestFile
import com.knightbyte.answers.domain.utils.DomainMapper

class ListDriveDtoMapper:DomainMapper<ListDriveEntity,TestFile> {
    override fun mapToDomain(entity: ListDriveEntity): TestFile {
        val fileName = entity.name
        val tempSplit= fileName.split("_")
        val testType = tempSplit[0]
        var testName = tempSplit[1]
        var testLevel = tempSplit[2].split(".")[0]
        return TestFile(
            driveId = entity.driveId,
            fileId = entity.id,
            mimeType = entity.mimeType,
            fileName = fileName,
            testType = testType,
            testName = testName,
            testLevel = testLevel
        )
    }

    override fun mapToDomainList(entities: MutableList<ListDriveEntity>): List<TestFile> {
        return entities.map { mapToDomain(it) }
    }
}