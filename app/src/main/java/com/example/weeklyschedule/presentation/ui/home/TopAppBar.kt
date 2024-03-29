package com.example.weeklyschedule.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weeklyschedule.R
import kotlinx.coroutines.launch

@Composable
fun TopAppBarScreen(scaffoldState: ScaffoldState) {
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxWidth(1f)) {
        TopAppBar(
            elevation = 5.dp,
            backgroundColor = Color(0xFFE9C6F1)
        ) {
            IconButton(onClick = {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Default.MoreVert, contentDescription = "", tint = Color.Black)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = stringResource(R.string.top_appBar_title), color = Color.Black)
        }

    }

}