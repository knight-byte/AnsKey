package com.knightbyte.answers.network.cache

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File

class AppFiles() {

    fun getCurDir(context: Context): File {
        return context.filesDir
    }

    fun getFilesList(context: Context): Int {
        val files: Array<String> = context.fileList()
        return files.size
    }

    fun getFiles(context: Context): Array<String> {
        val files: Array<String> = context.fileList()
        return files
    }

}