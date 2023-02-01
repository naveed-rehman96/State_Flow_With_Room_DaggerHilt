package com.navdroid.kotlinFlowSample.repo

import com.navdroid.kotlinFlowSample.dao.MessageDao
import com.navdroid.kotlinFlowSample.model.MessageModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessageRepo @Inject constructor(
    private val messageDao: MessageDao
) {
    suspend fun insertMessage(message: MessageModel) {
        messageDao.insertMessage(message)
    }

    fun getAllMessages(): Flow<List<MessageModel>> = messageDao.fetchAllRecords()
}
