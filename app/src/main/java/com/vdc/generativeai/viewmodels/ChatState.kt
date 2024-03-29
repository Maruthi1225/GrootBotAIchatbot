package com.vdc.generativeai.viewmodels

import android.graphics.Bitmap
import com.vdc.generativeai.data.Chat

data class ChatState(
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null // Added for error handling
)