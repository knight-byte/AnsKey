package com.knightbyte.answers.network.cache

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File

class AppFiles() {


    fun getCurDir(context: Context): File {
        return context.filesDir
    }

    fun getFileListSize(context: Context): Int {

//        val path2 = context.getExternalFilesDir(String())!!.path
//        val path: String = Environment.getExternalStorageDirectory().toString() + "/Documents/AnsKey"
//        val dir: File = File(path)
//        val dir2: File = File(path2)
//        Log.d("Test", "dir = ${dir}")
//        Log.d("Test", "dir2 = ${dir2}")
//        if (dir2.exists()) {
//            val test = dir2.listFiles()
//            Log.d("length", "${test!!.size}")
//        }
//        else {
//            Log.d("Test", "sahi se nahi ho raha")
//        }
        val tempPath = File(context.getExternalFilesDir(String())!!.path)
        val tempAbsPath = File(context.getExternalFilesDir(String())!!.absolutePath)
        val tempCanPath = File(context.getExternalFilesDir(String())!!.canonicalPath)
        Log.d("tempPath", "${tempPath}")
        Log.d("tempAbsPath", "${tempAbsPath}")
        Log.d("tempCanPath", "${tempCanPath}")

        val newPath = context.getDir(Environment.DIRECTORY_DOCUMENTS, Context.MODE_PRIVATE)
        Log.d("newPath", "${newPath}")

        val files: Array<String> = context.fileList()
        return files.size
    }

    fun getFiles(context: Context): Array<String> {
        val files: Array<String> = context.fileList()
        return files
    }

}