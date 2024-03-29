package com.vdc.generativeai.viewmodels

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import kotlinx.coroutines.flow.MutableStateFlow

val uriState = MutableStateFlow("")
@Composable
fun getBitmap(uri: String?): Bitmap? {
    uri ?: return null // Check if URI is null

    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .size(Size.ORIGINAL)
            .build()
    )

    // Check if the image has loaded successfully
    if (imagePainter.state is AsyncImagePainter.State.Success) {
        return (imagePainter.state as AsyncImagePainter.State.Success).result.drawable.toBitmap()
    }

    return null
}