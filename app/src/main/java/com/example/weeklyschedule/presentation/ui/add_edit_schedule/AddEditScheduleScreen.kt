package com.example.weeklyschedule.presentation.ui.add_edit_schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weeklyschedule.R
import com.example.weeklyschedule.data.local.DaysList
import com.example.weeklyschedule.presentation.ui.add_edit_schedule.component.DropDownSelection
import com.example.weeklyschedule.presentation.ui.theme.fontDefault

@Composable
fun AddEditScheduleScreen(
    navController: NavController,
    viewModel: AddEditViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(16.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(stringResource(R.string.add_days_week_label))
                    DropDownSelection(listContents = DaysList, label = "")
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "افزودن تعداد زنگ ها در یک روز",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        fontFamily = fontDefault,
                        fontSize = 16.sp,
                        color = Color.White
                    )

                    TextFieldWithIcons("تعداد زنگ ها", "اضافه کردن زنگ ها", Icons.Default.DateRange)
                }

                Row {
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(color = Color.Black), onClick = { }) {
                        Text("افزودن")
                    }
                }
            }
        }
    }

}