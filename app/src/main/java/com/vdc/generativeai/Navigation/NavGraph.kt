package com.vdc.generativeai.Navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vdc.generativeai.screens.TextChatScreen
import com.vdc.generativeai.screens.ImageChatScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(navController = navController,
        startDestination = Screens.Text2Text.route ){
        composable(Screens.Text2Text.route){
            TextChatScreen(paddingValues = innerPadding)
        }
        composable(Screens.ImageText2Text.route){
            ImageChatScreen(paddingValues = innerPadding)
        }
    }
}
