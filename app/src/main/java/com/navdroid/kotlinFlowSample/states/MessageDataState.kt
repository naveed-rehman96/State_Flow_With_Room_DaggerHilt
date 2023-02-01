package com.navdroid.kotlinFlowSample.states

import com.navdroid.kotlinFlowSample.model.MessageModel
import kotlinx.coroutines.flow.Flow

sealed class MessageDataState {

    object Empty : MessageDataState()
    object Loading : MessageDataState()
    class Failure(val error: Throwable) : MessageDataState()
    class Success(var response: List<MessageModel>) : MessageDataState()
}
