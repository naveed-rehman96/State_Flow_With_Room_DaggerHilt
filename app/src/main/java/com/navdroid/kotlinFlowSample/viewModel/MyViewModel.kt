package com.navdroid.kotlinFlowSample.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navdroid.kotlinFlowSample.model.MessageModel
import com.navdroid.kotlinFlowSample.repo.MessageRepo
import com.navdroid.kotlinFlowSample.states.MessageDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.* // ktlint-disable no-wildcard-imports
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repo: MessageRepo) : ViewModel() {

    fun insertMessage(messageModel: MessageModel) {
        viewModelScope.launch {
            repo.insertMessage(messageModel)
        }
    }

    private val _allCvListStateFlow = MutableStateFlow<MessageDataState>(MessageDataState.Empty)
    val allCvListStateFlow: StateFlow<MessageDataState> = _allCvListStateFlow

    fun getAllMessages() = viewModelScope.launch {
        repo.getAllMessages().onStart {
            _allCvListStateFlow.value = MessageDataState.Loading
        }.catch {
            _allCvListStateFlow.value = MessageDataState.Failure(it)
        }.collect {
            _allCvListStateFlow.value = MessageDataState.Success(it as MutableList<MessageModel>)
        }
    }
}
