package com.example.weeklyschedule.presentation.ui.home

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
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


@Composable
fun HomeScreen(
    navController: NavController, viewModel: HomeViewModel = hiltViewModel(),
    shareViewModel:ShareViewModel= hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val imageVector: Drawable

    shareViewModel.addCourses()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBarScreen() }
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
                        val today=Utilities().shamsiToday
                        DisplayTxtClock(" امروز :$today \n "+viewModel.showTime())
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
                        CustomListView(context = LocalContext.current,viewModel.coursesList)
                    }
                }
            }
        }
    }
}

