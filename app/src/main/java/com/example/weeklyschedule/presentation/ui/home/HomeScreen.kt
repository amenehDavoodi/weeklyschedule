package com.example.weeklyschedule.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weeklyschedule.R
import com.example.weeklyschedule.presentation.ui.ShareViewModel
import com.example.weeklyschedule.presentation.ui.dateUtils.Utilities
import com.example.weeklyschedule.presentation.ui.home.drawer.Drawer
import com.example.weeklyschedule.presentation.ui.util.Screen


@Composable
fun HomeScreen(
    navController: NavController, homeViewModel: HomeViewModel = hiltViewModel(),
    shareViewModel: ShareViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val allCourse by shareViewModel.allCourse.collectAsState(
        initial = emptyList()
    )
    val allCourseOfADay = homeViewModel.courseOfADay.collectAsState(
        initial = emptyList()
    )
    val allWeek = shareViewModel.allWeek.collectAsState(
        initial = emptyList()
    )


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBarScreen(scaffoldState) },
        drawerContent = {
            Drawer()
        },
        drawerGesturesEnabled = true,
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(16.dp)
        ) {
            item {

                Column(modifier = Modifier.fillMaxSize()) {

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier
                                .size(58.dp),
                            shape = CircleShape,
                            elevation = 2.dp,

                            ) {
                            Image(
                                painterResource(R.drawable.clock),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        val today = Utilities().shamsiToday
                        DisplayTxtClock(" امروز :$today \n " + homeViewModel.showTime())
                    }
                    Row {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = Utilities().shamsiToday,
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = 15.sp

                        )

                    }
                    Row {
                        CustomListView(context = LocalContext.current, allCourse, shareViewModel)
                    }
                    Row {
                        if (allWeek.value.isEmpty()) {
                            Icon(Icons.Filled.Warning, "", tint = Color.Red)
                            Text(

                                text = "هیچ درسی نیست که نمایش داده بشه !!\n درسها رو الان اضافه کن :)",

                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate(Screen.AddEditScreen.route)
//                                        navController.navigate(Screen.AddEditScreen)
                                    },
                                fontSize = 15.sp,
                                color = Color.Black, textAlign = TextAlign.Center
                            )
                        } else {
                            CustomListView(
                                context = LocalContext.current,
                                allCourseOfADay.value,
                                shareViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

