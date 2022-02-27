package com.knightbyte.answers.utils

import com.knightbyte.answers.network.response.CredDataResponse

const val CUSTOM_INFO_DEBUG_LOG = "-- [ INFO ] --"
const val CUSTOM_ERROR_DEBUG_LOG = "-- [ ERROR ] --"
const val CUSTOM_CHECK_DEBUG_LOG = "-- [ CHECK-LOG ] --"

const val DRIVE_API_BASE_URL = "https://www.googleapis.com/drive/v3/"
const val DRIVE_TOKEN_BASE_URL = "https://www.googleapis.com/oauth2/v4/"

class Constants{
    companion object{
        var DriveCredential : CredDataResponse?=null
        var Token : String ?= null
    }
}