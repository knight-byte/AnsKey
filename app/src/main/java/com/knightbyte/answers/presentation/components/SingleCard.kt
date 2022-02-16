package com.knightbyte.answers.presentation.components

import android.R.attr
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.knightbyte.answers.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.knightbyte.answers.presentation.ui.theme.MyPurple500
import com.knightbyte.answers.presentation.ui.theme.MyPurple700
import com.knightbyte.answers.presentation.ui.theme.promptSans
import com.knightbyte.answers.presentation.viewmodel.AnswersViewModel
import com.knightbyte.answers.utils.CUSTOM_ERROR_DEBUG_LOG
import java.io.File
import android.R.attr.mimeType
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Environment

import androidx.core.content.FileProvider


@Composable
fun SingleCard(
    fileName: String,
    answersViewModel: AnswersViewModel,
    fileId: String = "",
    delete: Boolean = false
) {
    val context = LocalContext.current
    val testName: String?
    val title: String?
    try {
        val parser = answersViewModel.fileNameParser(fileName)
        title = parser["title"]
        testName = parser["testName"]
    } catch (t: Throwable) {
        Log.d(CUSTOM_ERROR_DEBUG_LOG, "Fail to Parse Name, Error : $t")
        return
    }

    val file =
        File("${answersViewModel.appFile.getDocumentsPath(context = context).absolutePath}/${fileName}")
    var showDownloadButton = true
    var downloadAction = {}
    if (file.isFile) {
        showDownloadButton = false
    } else {

        downloadAction = {
            if (title != null) {
                answersViewModel.fileDownloader(
                    fileId = fileId,
                    outputName = fileName,
                    desc = title,
                    context = context
                )
            }
        }
    }

    Card(
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MyPurple500)
                .padding(
                    start = 30.dp,
                    end = 30.dp,
                )
                .clickable(
                    onClick = {
                        if (!showDownloadButton) {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.setAction(android.content.Intent.ACTION_VIEW)
                            val temp = File(context.getExternalFilesDir(
                                Environment.DIRECTORY_DOCUMENTS.toString() + File.separator + fileName)!!.absolutePath
                            )
                            val tempUri: Uri = FileProvider.getUriForFile(
                                context,
                                context.applicationContext.packageName + ".provider",
                                temp
                            )
                            //Log.d("filename", "${temp}")
                            intent.setDataAndType(tempUri, "application/pdf")
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            val possibleActivitiesList: List<ResolveInfo> =
                                context.packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL)
                            if (possibleActivitiesList.size > 1) {
                                val dialogText: String = "Choose a PDF Viewer"
                                val chooser = dialogText.let { title ->
                                    Intent.createChooser(intent, title)
                                }
                                context.startActivity(chooser)
                            }
                            else if (intent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(intent)
                            }
                        }
                        else {
                            Toast.makeText(context, "Download the file before opening", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                )
        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_card_icon_svg),
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    contentDescription = "",
                )
                Spacer(modifier = Modifier.width(20.dp))
                val textStyle = TextStyle(
                    color = MyPurple700,
                    fontFamily = promptSans
                )
                Column {
                    if (title != null) {
                        Text(
                            text = title,
                            style = textStyle,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    if (testName != null) {
                        Text(
                            text = testName,
                            style = textStyle,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
            if (showDownloadButton) {
                Image(
                    painter = painterResource(R.drawable.ic_download2_icon_svg),
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .align(Alignment.CenterEnd)
                        .clickable(onClick = downloadAction),
                    contentDescription = "",
                )
            } else if (delete) {
                val deleteAction = {
                    if (file.delete()) {
                        Toast.makeText(
                            context,
                            "$fileName Deleted Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(context, "Fail to delete $fileName", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                Icon(
                    painter = painterResource(R.drawable.ic_remove),
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .align(Alignment.CenterEnd)
                        .clickable(onClick = deleteAction),
                    contentDescription = "",
                    tint = MyPurple700
                )
            }
        }

    }
}
