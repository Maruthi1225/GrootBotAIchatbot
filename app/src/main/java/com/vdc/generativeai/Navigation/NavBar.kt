package com.vdc.generativeai.Navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vdc.generativeai.R

@Composable
fun NavBarHeader(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ai),
                contentDescription = "logo",
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 10.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.vaag),
                contentDescription = "logo",
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 10.dp)
            )

        }


        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.padding(top = 10.dp)
            )
    }
}

@Composable
fun NavBarBody(
    items : List<NavigationItem>,
    currentRoute : String?,
    onClick : (NavigationItem) -> Unit
){
   items.forEachIndexed{ index: Int, navigationItem: NavigationItem ->
       NavigationDrawerItem(
           label = {
                   Text( text = navigationItem.title)
           },
           selected = currentRoute == navigationItem.route  ,
           onClick = {
               onClick(navigationItem)
           },
           icon = {
               Icon(
                   imageVector = if (currentRoute == navigationItem.route) {
                       navigationItem.seletionIcon
                   } else {
                          navigationItem.unSeletionIcon
                          },
                   contentDescription = navigationItem.title
               )
           },
           badge = {
                   navigationItem.badgeCount?.let{
                       Text(text = it.toString())
                   }
           },
           modifier = Modifier.padding(
               PaddingValues(
                   horizontal = 12.dp,
                   vertical = 8.dp
               )

           )
       )
   }
}