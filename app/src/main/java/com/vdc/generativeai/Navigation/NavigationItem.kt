package com.vdc.generativeai.Navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title : String,
    val route : String,
    val seletionIcon : ImageVector,
    val unSeletionIcon : ImageVector,
    val badgeCount : Int? = null
)
