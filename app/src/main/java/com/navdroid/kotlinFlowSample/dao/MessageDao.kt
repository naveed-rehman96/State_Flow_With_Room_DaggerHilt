package com.navdroid.kotlinFlowSample.dao

import androidx.room.* // ktlint-disable no-wildcard-imports
import com.navdroid.kotlinFlowSample.model.MessageModel

@Dao
interface MessageDao {

    @Transaction
    fun updateMessage(message: MessageModel) {
        message.let {
            deleteAlLMessages() // comment it out if you want to add new message without deleting previous method
            insertMessage(it)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(messageModel: MessageModel)

    @Query("DELETE FROM MyTable")
    fun deleteAlLMessages()

    @Query("SELECT * FROM MyTable")
    fun getAllMessages(): List<MessageModel>

    @Query("SELECT * FROM MyTable")
    fun fetchAllRecords(): List<MessageModel>
}
