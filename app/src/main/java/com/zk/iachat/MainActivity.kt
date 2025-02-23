package com.zk.iachat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zk.iachat.ui.theme.IAChatTheme
import com.zk.iachat.view.ChatViewHome
import com.zk.iachat.view.ModalView
import com.zk.iachat.viewModel.GeminiVM


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val geminiVM : GeminiVM by viewModels()
        setContent {
            IAChatTheme {
                ChatViewHome(geminiVM)
            }
        }
    }
}
