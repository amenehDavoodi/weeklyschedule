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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weeklyschedule.R
import com.example.weeklyschedule.data.local.CourseList
import com.example.weeklyschedule.presentation.ui.add_edit_schedule.component.DropDownSelection


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
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(

                        text = stringResource(R.string.title_count_of_breaks_a_day),
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    TextFieldWithIcons(stringResource(R.string.label_breaks), "", Icons.Default.DateRange)
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(stringResource(R.string.add_days_week_label))
                    DropDownSelection(listContents = viewModel.dayList.value, label = "")
                }

                Row {
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp), onClick = {
                        for (i in 1 until (CourseList.size)) {
                            viewModel.addNewCourse(i,CourseList[i])
                        }
                    }) {
                        Text(stringResource(R.string.btn_add))
                    }
                }
            }
        }
    }

}