package com.knightbyte.answers.presentation.viewmodel

import android.annotation.TargetApi
import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.knightbyte.answers.domain.model.TestFile
import com.knightbyte.answers.repository.DriveFileRepository
import com.knightbyte.answers.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.knightbyte.answers.network.cache.AppFiles
import com.knightbyte.answers.repository.AuthRepository
import com.knightbyte.answers.utils.CUSTOM_INFO_DEBUG_LOG
import com.knightbyte.answers.utils.DRIVE_API_BASE_URL
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import me.xdrop.fuzzywuzzy.FuzzySearch

@HiltViewModel
class AnswersViewModel @Inject constructor(
    private val listFileRepository: DriveFileRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    val allFiles: MutableState<Resource<List<TestFile>>> = mutableStateOf(Resource.Empty())
    val homeCategory: MutableState<String> = mutableStateOf("")
    val appFile = AppFiles()
    val token: MutableState<Resource<String>> = mutableStateOf(Resource.Empty())

    init {
        loadFiles()
    }

    fun loadFiles() {
        if (allFiles.value.data == null || allFiles.value is Resource.Empty) {
            allFiles.value = Resource.Loading()
            viewModelScope.launch {
                allFiles.value = listFileRepository.listFiles()
            }
            generateToken()
        }

    }

    fun searchFiles(
        search: String
    ): MutableList<TestFile> {
        if (allFiles.value is Resource.Empty || allFiles.value.data == null) {
            return mutableListOf()
        }
        if (search.isEmpty()) return allFiles.value.data?.toMutableList()!!
        val result: MutableList<TestFile> = mutableListOf()
        for (str in allFiles.value.data!!) {
            val testString = "${str.testName} ${str.fileName}".lowercase()
            val ratio = FuzzySearch.partialRatio(search.lowercase(), testString)
            if (ratio > 50) {
                result.add(str)
            }
        }
        return result
    }
    private fun getUrl(fileId: String): String {
        return "${DRIVE_API_BASE_URL}files/${fileId}?supportAllDrives=True&alt=media"
    }
    fun fileDownloader(
        fileId: String,
        outputName: String,
        desc: String = "",
        context: Context
    ) {
        val url = getUrl(fileId = fileId)
        val authToken = token.value.data
        Log.d(CUSTOM_INFO_DEBUG_LOG,"URL : $url\n TOKEN : $authToken")
        val request = DownloadManager.Request(Uri.parse(url))
            .addRequestHeader("Authorization", authToken)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setTitle(outputName)
            .setDescription(desc)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
            .setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOCUMENTS, outputName)


        val dm = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(request)
    }

    fun generateToken() {
        token.value = Resource.Loading()
        viewModelScope.launch {
            try {
                token.value = Resource.Success(authRepository.getToken())
            } catch (t: Throwable) {
                token.value = Resource.Error("ER : $t")
            }
        }
    }
}