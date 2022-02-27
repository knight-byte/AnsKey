package com.knightbyte.answers.presentation.viewmodel

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.knightbyte.answers.domain.model.TestFile
import com.knightbyte.answers.repository.DriveFileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.knightbyte.answers.network.cache.AppFiles
import com.knightbyte.answers.repository.AuthRepository
import com.knightbyte.answers.utils.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import me.xdrop.fuzzywuzzy.FuzzySearch
import java.io.File

@HiltViewModel
class AnswersViewModel @Inject constructor(
    private val listFileRepository: DriveFileRepository,
    private val authRepository: AuthRepository,
    @ApplicationContext val context: Context
) : ViewModel() {
    val allFiles: MutableState<Resource<List<TestFile>>> = mutableStateOf(Resource.Empty())
    val homeCategory: MutableState<String> = mutableStateOf("")
    val appFile = AppFiles()
    val downloadedFile : MutableState<List<String>> = mutableStateOf(listOf())

    val token: MutableState<Resource<String>> = mutableStateOf(Resource.Empty())

    val firebaseStatus = CredentialProvider.firebaseStatus


    fun loadFiles() {

        if (allFiles.value !is Resource.Loading && (allFiles.value.data == null || allFiles.value is Resource.Empty )) {
//            generateToken()
            allFiles.value = Resource.Loading()
            viewModelScope.launch {
                allFiles.value = listFileRepository.listFiles()
            }
        }

    }

    fun updateDownloadFile(){
        downloadedFile.value = appFile.getFiles(context)
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
        val file = File("${appFile.getDocumentsPath(context).absolutePath}/${outputName}")
        if (file.isFile()) {
            return
        }
        val url = getUrl(fileId = fileId)
        val authToken = Constants.Token
        Log.d(CUSTOM_INFO_DEBUG_LOG, "URL : $url\n TOKEN : $authToken")

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
        updateDownloadFile()
    }

    private fun generateToken() {
        token.value = Resource.Loading()
        viewModelScope.launch {
            try {
                token.value = Resource.Success(authRepository.getToken())
            } catch (t: Throwable) {
                token.value = Resource.Error("ER : $t")
            }
        }
    }

    fun fileNameParser(
        fileName: String
    ): Map<String, String> {
        val tempSplit = fileName.split("_")
        val testType = tempSplit[0]
        val testName = tempSplit[1]
        val testLevel = tempSplit[2].split(".")[0]

        return mapOf(
            Pair("title", "$testType - $testName"),
            Pair("testName", testLevel)
        )

    }
}