package com.navdroid.kotlinFlowSample.states

import com.navdroid.kotlinFlowSample.model.MessageModel

sealed class MessageDataState {

    object Empty : MessageDataState()
    object Loading : MessageDataState()
    class Failure(val error: Throwable) : MessageDataState()
    class Success(var response: MutableList<MessageModel>) : MessageDataState()
}
