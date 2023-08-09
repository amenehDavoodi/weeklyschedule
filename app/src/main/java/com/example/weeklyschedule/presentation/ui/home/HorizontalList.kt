@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.weeklyschedule.presentation.ui.home

import android.content.Context
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weeklyschedule.data.local.entities.Courses
import com.example.weeklyschedule.presentation.ui.ShareViewModel
import com.example.weeklyschedule.presentation.ui.theme.Blue700
import com.example.weeklyschedule.presentation.ui.theme.Orange200
import com.example.weeklyschedule.presentation.ui.theme.Pink500
import com.example.weeklyschedule.presentation.ui.theme.Purple200
import com.example.weeklyschedule.presentation.ui.theme.Teal200
import com.example.weeklyschedule.presentation.ui.theme.Yellow200


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomListView(
    context: Context,
    courseList2: List<Courses>,
    shareViewModel: ShareViewModel = hiltViewModel(),
    onclick:OnClickListener
) {


    if (courseList2.isNotEmpty()) {
        LazyRow {
            itemsIndexed(courseList2) { index, _ ->
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

                            .background(getBackground(index))
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(5.dp))
                        val pic = courseList2[index].coursePic
                        Image(
                            painter = painterResource(id = pic),

                            contentDescription = "img",

                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(5.dp),

                            alignment = Alignment.Center
                        )

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
    } else {
        Icon(Icons.Filled.Warning, "", tint = Color.Red)

        Text(

            text = "هیچ درسی نیست که نمایش داده بشه !!\n درسها رو الان اضافه کن :)",

            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .clickable {
                    onclick
                },
            fontSize = 15.sp,
            color = Color.Black, textAlign = TextAlign.Center
        )
    }


}

fun getBackground(index: Int): Color {
    var bgColor: Color = Color.White
    when (index % 6) {
        0 -> bgColor = Purple200
        1 -> bgColor = Pink500
        2 -> bgColor = Blue700
        3 -> bgColor = Teal200
        4 -> bgColor = Orange200
        5 -> bgColor = Yellow200
    }
    return bgColor

}