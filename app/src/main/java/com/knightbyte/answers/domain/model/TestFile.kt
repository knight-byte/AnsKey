package com.knightbyte.answers.domain.model

// List Drive File model
data class TestFile(
    val fileId: String,
    val fileName: String,
    val testType:String,
    val testName:String,
    val testLevel:String,
    val mimeType: String,
    val driveId: String,
)