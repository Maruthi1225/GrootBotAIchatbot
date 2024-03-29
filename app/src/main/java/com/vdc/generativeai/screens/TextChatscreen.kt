package com.vdc.generativeai.screens

import android.annotation.SuppressLint
import com.vdc.generativeai.viewmodels.ChatViewModel
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vdc.generativeai.viewmodels.ChatUiEvent
import com.vdc.generativeai.viewmodels.ModelChatItem
import com.vdc.generativeai.R
import com.vdc.generativeai.viewmodels.LoadingAnimation
import com.vdc.generativeai.viewmodels.UserChatItem
import com.vdc.generativeai.viewmodels.getBitmap
import com.vdc.generativeai.viewmodels.uriState
import kotlinx.coroutines.flow.update


   @SuppressLint("StateFlowValueCalledInComposition")
   @Composable
    fun TextChatScreen(paddingValues: PaddingValues) {
        val chaViewModel = viewModel<ChatViewModel>()
        val chatState = chaViewModel.chatState.collectAsState().value
       var instructionsVisible by remember { mutableStateOf(true) }

       val bitmap = getBitmap(uriState.value)

        Box() {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Image(
                    painter = painterResource(id = R.drawable.ai),
                    contentDescription = "app logo",
                    Modifier
                        .size(300.dp)
                        .align(Alignment.CenterHorizontally)
                )
                if (instructionsVisible) {
                    Text(
                        text = stringResource(id = R.string.groot),
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = stringResource(id = R.string.how),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = "Please provide Text Prompt",
                        textAlign = TextAlign.Center
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding()),
                verticalArrangement = Arrangement.Bottom
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    reverseLayout = true
                ) {
                    itemsIndexed(chatState.chatList) { index, chat ->
                        if (chat.isFromUser) {
                            UserChatItem(
                                prompt = chat.prompt, bitmap = chat.bitmap
                            )
                        } else {
                            ModelChatItem(response = chat.prompt)
                        }
                    }
                }


                if (chatState.isLoading) {
                    LoadingAnimation(
                        isLoading = chatState.isLoading,
                        message = "",
                        size = 36.dp,
                        Modifier.padding(bottom = 50.dp, start = 10.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Loading indicator
                if (chatState.isLoading) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .padding(vertical = 4.dp)
                            .padding(top = 5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, start = 4.dp, end = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(8.dp))

                    TextField(
                        modifier = Modifier
                            .weight(1f),
                        value = chatState.prompt,
                        onValueChange = {
                            chaViewModel.onEvent(ChatUiEvent.UpdatePrompt(it))
                            if (it.isNotEmpty()) {
                                instructionsVisible = false
                            } else {
                                instructionsVisible = true
                            }
                        },
                        placeholder = {
                            Text(text = "Type a prompt")
                        },
                        keyboardActions = KeyboardActions(
                            onSend = {
                                chaViewModel.onEvent(
                                    ChatUiEvent.SendPrompt(
                                        chatState.prompt,
                                        bitmap
                                    )
                                )
                                uriState.update { "" }
                            }
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Send
                        ),
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                chaViewModel.onEvent(
                                    ChatUiEvent.SendPrompt(
                                        chatState.prompt,
                                        bitmap
                                    )
                                )
                                uriState.update { "" }
                            },
                        imageVector = Icons.Rounded.Send,
                        contentDescription = "Send prompt",
                        tint = MaterialTheme.colorScheme.primary
                    )

                }

            }

        }
    }
