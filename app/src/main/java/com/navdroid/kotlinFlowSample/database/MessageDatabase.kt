package com.navdroid.kotlinFlowSample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.navdroid.kotlinFlowSample.dao.MessageDao
import com.navdroid.kotlinFlowSample.model.MessageModel

private const val DB_NAME = "MySampleDatabase"

@Database(entities = [(MessageModel::class)], version = 1 , exportSchema = false)
abstract class MessageDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDao

    companion object {
        lateinit var instance: MessageDatabase
        fun getDatabaseInstance(context: Context): MessageDatabase {
            instance = Room.databaseBuilder(
                context.applicationContext,
                MessageDatabase::class.java,
                "MySampleDatabase"
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addCallback(roomCallBack)
                .build()

            return instance
        }
        private val roomCallBack: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}
