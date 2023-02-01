package com.navdroid.kotlinFlowSample.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyTable")
data class MessageModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val message: String = ""
)
