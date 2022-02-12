package com.knightbyte.answers.presentation.viewmodel

import android.annotation.TargetApi
import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION_CODES.M
import android.os.Environment
import android.os.FileUtils
import androidx.appcompat.app.AlertDialog
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
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import me.xdrop.fuzzywuzzy.FuzzySearch
import androidx.core.net.toUri
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.jar.Manifest

@HiltViewModel
class AnswersViewModel @Inject constructor(
    private val listFileRepository: DriveFileRepository,
) : ViewModel() {
    val allFiles: MutableState<Resource<List<TestFile>>> = mutableStateOf(Resource.Empty())
    val homeCategory : MutableState<String> = mutableStateOf("")
    val appFile = AppFiles()

    init {
        loadFiles()
    }

    fun loadFiles() {
        if (allFiles.value.data == null || allFiles.value is Resource.Empty) {
            allFiles.value = Resource.Loading()
            viewModelScope.launch {
                allFiles.value = listFileRepository.listFiles()
            }
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

    fun fileDownloader(url: String, outputName: String, desc: String, context: Context) {
        //val dir = appFile.getCurDir(context)
        val appDir: String = "AnswerKey"
        val dir = File(Environment.DIRECTORY_DOCUMENTS)
        if (!dir.exists())
            dir.mkdirs()
        //val appDirUri = dir.toUri()
        val request = DownloadManager.Request(Uri.parse(url))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setTitle(outputName)
            .setDescription(desc)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
            .setDestinationInExternalPublicDir(dir.toString(), "AnsKey/${outputName}")

        val dm = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(request)
    }
}