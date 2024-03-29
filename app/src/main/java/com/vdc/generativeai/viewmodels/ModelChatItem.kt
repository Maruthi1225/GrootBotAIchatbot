package com.vdc.generativeai.viewmodels

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vdc.generativeai.R
import com.vdc.generativeai.ui.theme.chatModel
import com.vdc.generativeai.ui.theme.textChat

@Composable
fun ModelChatItem(response: String) {
    Column(
        modifier = Modifier.padding(end = 25.dp, bottom = 16.dp)
    ) {
        Row{
            Image(
                painter = painterResource(id = R.drawable.ai),
                contentDescription ="ai",
                Modifier
                    .size(26.dp)
                    .clip(RoundedCornerShape(100.dp))
            )
            Text(
                text = stringResource(id = R.string.app_name),
                Modifier.padding(bottom = 5.dp)
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(chatModel)
                .padding(16.dp),
            text = response,
            fontSize = 14.sp,
            color = textChat
        )
    }
}