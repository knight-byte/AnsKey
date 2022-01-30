package com.knightbyte.answers.network.response

import com.knightbyte.answers.network.model.ListDriveEntity

data class ListDriveResponse(
    val kind: String,
    val nextPageToken: String?,
    val incompleteSearch: Boolean,
    val files: List<ListDriveEntity>
)
