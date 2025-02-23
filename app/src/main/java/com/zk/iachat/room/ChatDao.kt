package com.zk.iachat.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zk.iachat.model.ChatModel

@Dao
interface ChatDao {

    @Query("SELECT * FROM chatBot order by id ASC")
    suspend fun getChat(): List<ChatModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(item: ChatModel)

    @Query("DELETE FROM chatBot")
    suspend fun deleteChat()

}