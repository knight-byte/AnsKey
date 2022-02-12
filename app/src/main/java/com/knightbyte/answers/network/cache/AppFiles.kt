package com.knightbyte.answers.network.cache

import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import com.knightbyte.answers.utils.CUSTOM_CHECK_DEBUG_LOG
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File

class AppFiles {


    fun getCurDir(context: Context): File {
        return context.filesDir
    }

    fun getDocumentsPath(context:Context): File {
        return File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!.absolutePath)
    }

    fun getFileListSize(context: Context): Int {
        val allFiles = getDocumentsPath(context).listFiles()
        val files : MutableList<String> = mutableListOf()
        allFiles.forEach { file ->
            if (file.isFile){
                files.add(file.name)
                val fileSize = file.length()/(1024.0)
                println("f - ${file.name} - ${fileSize} KB")
            }
        }

        return files.size
    }

    fun getFiles(context: Context): Array<String> {
        val files: Array<String> = context.fileList()
        return files
    }

}