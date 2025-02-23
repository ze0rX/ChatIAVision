package com.zk.iachat.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chatBot")
data class ChatModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "chat") val chat: String,
    @ColumnInfo(name = "role") val role: String,
)
