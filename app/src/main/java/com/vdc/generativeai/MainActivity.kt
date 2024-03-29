package com.vdc.generativeai


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import com.vdc.generativeai.Navigation.NavBarHeader
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.TextSnippet
import androidx.compose.material.icons.outlined.ImageSearch
import androidx.compose.material.icons.outlined.TextSnippet
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vdc.generativeai.Navigation.NavBarBody
import com.vdc.generativeai.Navigation.NavGraph
import com.vdc.generativeai.Navigation.NavigationItem
import com.vdc.generativeai.Navigation.Screens
import com.vdc.generativeai.ui.theme.GenerativeAiTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GenerativeAiTheme {
                val items = listOf(
                    NavigationItem(
                        title = "Text-2-Text",
                        route = Screens.Text2Text.route,
                        seletionIcon = Icons.Filled.TextSnippet,
                        unSeletionIcon = Icons.Outlined.TextSnippet
                    ),
                    NavigationItem(
                        title = "Image/Text-2-Text",
                        route = Screens.ImageText2Text.route,
                        seletionIcon = Icons.Filled.ImageSearch,
                        unSeletionIcon = Icons.Outlined.ImageSearch
                    ),

                    )

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val navController: NavHostController = rememberNavController()
                val context = LocalContext.current

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                /* val topBarTitle =
                     if (currentRoute != null){
                         items[items.indexOfFirst {
                             it.route == currentRoute
                         }].title
                     } else {
                         items[0].title
                     }*/

                ModalNavigationDrawer(
                    gesturesEnabled = drawerState.isOpen,
                    drawerContent = {
                        ModalDrawerSheet {
                            NavBarHeader()
                            Spacer(modifier = Modifier.height(8.dp))
                            NavBarBody(
                                items = items,
                                currentRoute = currentRoute
                            ) { currentnavigationItem ->
                                if (currentnavigationItem.route == "Image/Text-2-Text") {
                                    Toast.makeText(context, "Image/Text-2-Text", Toast.LENGTH_LONG)
                                        .show()
                                }
                                navController.navigate(currentnavigationItem.route) {
                                    navController.graph.startDestinationRoute?.let {
                                        popUpTo(it) {
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        }

                    }, drawerState = drawerState
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(

                                title = {
                                    Text(text = stringResource(id = R.string.app_name))

                                },

                                navigationIcon = {
                                    IconButton(onClick = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = "menu"
                                        )
                                    }
                                }
                            )
                        }
                    ) { innerpadding ->
                        NavGraph(navController = navController, innerPadding = innerpadding)
                    }
                }
            }
        }
    }
}