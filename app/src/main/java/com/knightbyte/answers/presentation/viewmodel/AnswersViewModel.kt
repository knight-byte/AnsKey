package com.knightbyte.answers.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.knightbyte.answers.domain.model.TestFile
import com.knightbyte.answers.repository.DriveFileRepository
import com.knightbyte.answers.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.xdrop.fuzzywuzzy.FuzzySearch

@HiltViewModel
class AnswersViewModel @Inject constructor(
    private val listFileRepository: DriveFileRepository,
) : ViewModel() {
    val allFiles: MutableState<Resource<List<TestFile>>> = mutableStateOf(Resource.Empty())
    val homeCategory : MutableState<String> = mutableStateOf("")
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
}