package com.example.weeklyschedule.presentation.ui.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weeklyschedule.data.local.entities.Courses

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun customListView(context: Context,courseList2: List<Courses>) {


    LazyRow {
        itemsIndexed(courseList2) { index, item ->
            Card(
                onClick = {
                    Toast.makeText(
                        context,
                        courseList2[index].courseName + " selected..",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .width(120.dp),
                elevation = 6.dp
            )
            {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(5.dp))

//                    Image(
//                        painter = painterResource(id = courseList[index].courseId),
//
//                        contentDescription = "img",
//
//                        modifier = Modifier
//                            .height(60.dp)
//                            .width(60.dp)
//                            .padding(5.dp),
//
//                        alignment = Alignment.Center
//                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(

                        text = courseList2[index].courseName,

                        modifier = Modifier.padding(4.dp),

                        color = Color.Black, textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}