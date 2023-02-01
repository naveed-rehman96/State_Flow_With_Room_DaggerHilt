package com.navdroid.kotlinFlowSample.repo

import com.navdroid.kotlinFlowSample.dao.MessageDao
import com.navdroid.kotlinFlowSample.model.MessageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MessageRepo @Inject constructor(
    private val messageDao: MessageDao
) {
    suspend fun insertMessage(message: MessageModel) {
        messageDao.insertMessage(message)
    }

    fun getAllMessages(): Flow<List<MessageModel>> = flow {
        emit(messageDao.fetchAllRecords())
    }.flowOn(Dispatchers.Default)
}
