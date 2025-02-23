package com.zk.iachat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zk.iachat.model.MessageModel
import com.zk.iachat.ui.theme.focusColor
import com.zk.iachat.ui.theme.unfocusedColor

@Composable
fun MessageInputView(onClick: (String) -> Unit) {
    var message by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            value = message,
            onValueChange = { message = it },
            placeholder = { Text("Esperando mensaje ... ",color = Color.White) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = focusColor,
                unfocusedIndicatorColor = unfocusedColor,
                focusedTextColor = Color.White
            ),
        )

        IconButton(onClick = {
            onClick(message)
            message = ""
        }) {
            Icon(imageVector = Icons.AutoMirrored.Default.Send, contentDescription = "Send",tint = Color.DarkGray)
        }
    }
}

/*
@Composable
fun GlobalMessage(messageModel: MessageModel) {
    val rolmodel = messageModel.role == "model"
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .align(
                        if (rolmodel)
                            Alignment.CenterStart
                        else
                            Alignment.CenterEnd
                    )
                    .padding(10.dp)
                    .clip(RoundedCornerShape(30f))
                    .background(if (rolmodel) Color.DarkGray else Color.Transparent)
            ) {
                Text(
                    text = messageModel.message,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}
*/


@Composable
fun GlobalMessage(messageModel: MessageModel) {
    val rolmodel = messageModel.role == "model"
    val visible = remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .align(
                        if (rolmodel)
                            Alignment.CenterStart
                        else
                            Alignment.CenterEnd
                    )
                    .padding(10.dp)
                    .clip(RoundedCornerShape(30f))
                    .background(if (rolmodel) Color.DarkGray else Color.Transparent)
            ) {
                Text(
                    text = messageModel.message,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}


@Composable
fun Title() {
    Text(
        "IAChat",
        fontWeight = FontWeight.Black,
        color = Color.White
    )
}