package com.example.weeklyschedule.presentation.ui.home

import android.widget.TextClock
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DisplayTxtClock() {

    Column(

        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Text Clock in Android",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 25.sp,
        )
        Spacer(modifier = Modifier.height(20.dp))
        AndroidView(
            factory = { context ->
                TextClock(context).apply {
                    format12Hour?.let { this.format12Hour = "hh:mm:ss a" }
                    timeZone?.let { this.timeZone = it }
                    textSize.let { this.textSize = 35f }
                }
            },
            modifier = Modifier.padding(5.dp)
        )
    }
}