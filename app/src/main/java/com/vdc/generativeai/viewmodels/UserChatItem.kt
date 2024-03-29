package com.vdc.generativeai.viewmodels

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vdc.generativeai.R
import com.vdc.generativeai.ui.theme.chatUser
import com.vdc.generativeai.ui.theme.textChat

@Composable
fun UserChatItem(prompt: String, bitmap: Bitmap?) {
    Column(
        modifier = Modifier.padding(start = 100.dp, bottom = 16.dp)
    ) {

        bitmap?.let {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .padding(bottom = 2.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                bitmap = it.asImageBitmap()
            )
        }


        Column {
            Row{
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription ="user",
                    Modifier
                        .size(26.dp)
                        .clip(RoundedCornerShape(100.dp))
                )
                Text(
                    text = "user",
                    Modifier.padding(bottom = 5.dp)
                )
            }


            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(chatUser)
                    .padding(10.dp),
                text = prompt,
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
                color = textChat
            )
        }

    }
}