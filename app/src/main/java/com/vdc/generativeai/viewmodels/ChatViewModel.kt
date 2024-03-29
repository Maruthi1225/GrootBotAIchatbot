package com.vdc.generativeai.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vdc.generativeai.data.Chat
import com.vdc.generativeai.data.ChatData


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ChatViewModel : ViewModel() {


    private val _chatState = MutableStateFlow(ChatState())
    val chatState = _chatState.asStateFlow()

    private fun setLoading(isLoading: Boolean, errorMessage: String? = null) {
        _chatState.update {
            it.copy(isLoading = isLoading, errorMessage = errorMessage)
        }
    }

    fun onEvent(event: ChatUiEvent) {
        when (event) {
            is ChatUiEvent.SendPrompt -> {
                if (event.prompt.isNotEmpty()) {
                    setLoading(true)
                    addPrompt(event.prompt, event.bitmap)

                    if (event.bitmap != null) {
                        getResponseWithImage(event.prompt, event.bitmap)
                    } else {
                        getResponse(event.prompt)
                    }
                }
            }

            is ChatUiEvent.UpdatePrompt -> {
                _chatState.update {
                    it.copy(prompt = event.newPrompt, errorMessage = null)
                }
            }
        }
    }

    private fun addPrompt(prompt: String, bitmap: Bitmap?) {
        _chatState.update {
            it.copy(
                chatList = it.chatList.toMutableList().apply {
                    add(0, Chat(prompt, bitmap, true))
                },
                prompt = "",
                bitmap = null,
                errorMessage = null
            )
        }
    }

    private fun getResponse(prompt: String) {
        viewModelScope.launch {
            setLoading(true) // Start loading animation
            try {
                val chat = ChatData.getResponse(prompt)
                _chatState.update {
                    it.copy(
                        chatList = it.chatList.toMutableList().apply {
                            add(0, chat)
                        }
                    )
                }
            } catch (e: Exception) {
                setLoading(false, "Error fetching response. Please try again.") // Set error message
            }
            setLoading(false) // Stop loading animation
        }
    }

    private fun getResponseWithImage(prompt: String, bitmap: Bitmap) {
        viewModelScope.launch {
            setLoading(true) // Start loading animation
            try {
                val chat = ChatData.getResponseWithImage(prompt, bitmap)
                _chatState.update {
                    it.copy(
                        chatList = it.chatList.toMutableList().apply {
                            add(0, chat)
                        }
                    )
                }
            } catch (e: Exception) {
                setLoading(false, "Error fetching response. Please try again.") // Set error message
            }
            setLoading(false) // Stop loading animation
        }
    }
}



