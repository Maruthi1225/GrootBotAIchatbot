package com.vdc.generativeai.viewmodels

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vdc.generativeai.ui.theme.chatModel

@Composable
fun LoadingAnimation(
    isLoading: Boolean,
    message: String,
    size: Dp,
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        Box(
            modifier = modifier
                .offset(x = 0.dp, y = size.plus(8.dp))
                .size(size, size)
                .background(chatModel)
                .padding(4.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                CircularProgressIndicator(strokeWidth = 2.dp)
                Text(
                    text = message,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp, bottom = 20.dp)

                )
            }

        }
    }
}
