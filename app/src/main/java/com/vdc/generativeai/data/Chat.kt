// File: ChatState.kt
package com.vdc.generativeai.data

import android.graphics.Bitmap

data class Chat (
    val prompt: String,
    val bitmap: Bitmap?,
    val isFromUser: Boolean
)
