package com.zk.iachat.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.zk.iachat.components.GlobalMessage
import com.zk.iachat.components.MessageInputView
import com.zk.iachat.components.Title
import com.zk.iachat.ui.theme.backgroundColor
import com.zk.iachat.viewModel.GeminiVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatViewHome(geminiVM : GeminiVM) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Title() },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1A1A1A),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { geminiVM.deleteChat() }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            )
        }
    ) { pad ->
        Column(
            modifier = Modifier.padding(pad).background(backgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var showModal by remember {
                mutableStateOf(false)
            }
            ChatViewContent(
                modifier = Modifier.weight(1f),
                geminiVM = geminiVM
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {showModal = true}) {
                    Icon(imageVector = Icons.Default.Camera, contentDescription = "Camera",tint = Color.DarkGray)
                }
                MessageInputView {
                    geminiVM.sendMessage(it)
                }
            }
            ModalView(geminiVM, showModal) {
                geminiVM.cleanVar()
                showModal = false
            }
        }
    }
}

@Composable
fun ChatViewContent(modifier: Modifier, geminiVM: GeminiVM) {
    LaunchedEffect(Unit) {
        geminiVM.loadChat()
    }
    LazyColumn(
        modifier = modifier,
        reverseLayout = true
    ) {
        items(geminiVM.messageList.reversed()) {
            GlobalMessage(messageModel = it)
        }
    }
}