package com.knightbyte.answers.network.model

data class ListDriveEntity(
    val kind: String,
    val id: String,
    val name: String,
    val mimeType: String,
    val teamDriveId: String,
    val driveId: String
)
