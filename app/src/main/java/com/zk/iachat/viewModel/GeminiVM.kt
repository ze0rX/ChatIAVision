package com.zk.iachat.viewModel

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.zk.iachat.BuildConfig
import com.zk.iachat.model.ChatModel
import com.zk.iachat.model.MessageModel
import com.zk.iachat.room.AppDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GeminiVM(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(application, AppDb::class.java, "chat_Bot").build()
    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.0-flash",
        apiKey = BuildConfig.apikey
    )

    private val chat by lazy {
        generativeModel.startChat()
    }

    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }

    /* fun sendMessage(question: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                messageList.add(MessageModel(question, "user"))
                val response = chat.sendMessage(question)
                messageList.add(MessageModel(response.text.toString(), "model"))
            }catch (e: Exception) {
                messageList.add(MessageModel("ERROR; ${e.message.toString()}", "model"))
            }
        }
     }*/

    fun sendMessage(question: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                messageList.add(MessageModel(question, "user"))
                val contextChat =
                    messageList.joinToString(separator = "\n") { "${it.role}: ${it.message}" }
                val response = chat.sendMessage(contextChat)
                messageList.add(MessageModel(response.text.toString(), "model"))

                //room
                val chatDao = db.chatDao()
                chatDao.insertChat(item = ChatModel(chat = question, role = "user"))
                chatDao.insertChat(
                    item = ChatModel(
                        chat = response.text.toString(),
                        role = "model"
                    )
                )
            } catch (e: Exception) {
                messageList.add(MessageModel("ERROR; ${e.message.toString()}", "model"))
            }
        }
    }

    fun loadChat() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val chatDao = db.chatDao()
                val savedChat = chatDao.getChat()
                messageList.clear()
                savedChat.forEach {
                    messageList.add(MessageModel(it.chat, it.role))
                }

            } catch (e: Exception) {
                messageList.add(MessageModel("ERROR; ${e.message.toString()}", "model"))
            }
        }
    }


    fun deleteChat() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val chatDao = db.chatDao()
                chatDao.deleteChat()
                messageList.clear()
            } catch (e: Exception) {
                messageList.add(MessageModel("ERROR; ${e.message.toString()}", "model"))
            }
        }
    }

    //ENVIAR A LA BASE DE DATOS DE GEMINI

    var descriptionResponse by mutableStateOf("")

    var image by mutableStateOf<Uri>(Uri.EMPTY)

    fun descriptionImage(bitmap: Bitmap) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val inputContent = content {
                    image(bitmap)
                    text("Describe la imagen que veas a continuacion, no necesitas detallar todo")
                }
                val response = generativeModel.generateContent(inputContent)
                descriptionResponse = response.text.toString()
            } catch (e: Exception) {
                descriptionResponse = "ERROR; ${e.message.toString()}"
            }
        }
    }

    fun cleanVar() {
        descriptionResponse = ""
        image = Uri.EMPTY
    }
}