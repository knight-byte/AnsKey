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

@HiltViewModel
class AnswersViewModel @Inject constructor(
    private val listFileRepository: DriveFileRepository,
) : ViewModel() {
    val allFiles: MutableState<Resource<List<TestFile>>> = mutableStateOf(Resource.Empty())

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
}