package com.zk.iachat.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zk.iachat.model.ChatModel

@Database(entities = [ChatModel::class], version = 1, exportSchema = false)
abstract class AppDb:RoomDatabase() {
    abstract fun chatDao(): ChatDao

}