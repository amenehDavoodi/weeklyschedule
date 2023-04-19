package com.example.weeklyschedule.presentation.ui.add_edit_schedule

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.weeklyschedule.R
import com.example.weeklyschedule.data.local.entities.WeeklySchedule
import com.example.weeklyschedule.presentation.ui.add_edit_schedule.component.DropDownSelection


@Composable
fun AddEditScheduleScreen(
    navController: NavController, viewModel: AddEditViewModel = hiltViewModel()
) {
    val selectedDay = remember { mutableStateOf("") } // initial value
    val userSelectedStringDay: (String) -> Unit = {
        selectedDay.value = it
    }
    val selectedCourses = remember { mutableStateOf("") } // initial value
    val userSelectedStringCourses: (String) -> Unit = {
        selectedCourses.value = it
    }
    val selectedBreaks = remember { mutableStateOf("") } // initial value
    val userSelectedStringBreaks: (String) -> Unit = {
        selectedBreaks.value = it
    }

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
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.title_count_of_breaks_a_day)
                        )
                        TextFieldWithIcons(
                            "",
                            "",
                            Icons.Default.DateRange,
                            Modifier.fillMaxSize(),
                            errorMsg = "لطفا عدد وارد کنید!"
                        )
                    }


                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(stringResource(R.string.add_days_week_label))
                    DropDownSelection(
                        listContents = viewModel.dayList.value,
                        label = "",
                        userSelectedStringDay
                    )
                }
                Row {

                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1F),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        DropDownSelection(
                            listContents = viewModel.breakList,
                            label = "",
                            userSelectedStringBreaks
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1F),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {


                        DropDownSelection(
                            listContents = viewModel.getListNames(viewModel.coursesList),
                            label = "", userSelectedStringCourses
                        )
                    }
                }

                Row {
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                        onClick = {
                            val dayId = viewModel.getSelectedId(selectedDay.value, viewModel.dayList.value)
                            val courseId = viewModel.getSelectedId(selectedCourses.value, viewModel.coursesList)
                            val breaksId = viewModel.getSelectedId(selectedBreaks.value, viewModel.breakList)
                            viewModel.addNewScheduleForADay(
                                WeeklySchedule(
                                    dayId = dayId,
                                    courseId = courseId,
                                    breakId = breaksId
                                )
                            )

                        }) {
                        Text(stringResource(R.string.btn_add))
                    }
                }
                Column {
                    val context = LocalContext.current

                    DisposableEffectWithLifeCycle(
                        onResume = {
                            Toast.makeText(
                                context,
                                "DisposableEffectWithLifeCycle onResume()",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        },
                        onPause = {
                            Toast.makeText(
                                context,
                                "DisposableEffectWithLifeCycle onPause()",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        },
                        onStart = {
                            Toast.makeText(
                                context,
                                "DisposableEffectWithLifeCycle onStart()",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }, viewModel

                    )
                }
            }
        }
    }

}

@Composable
private fun DisposableEffectWithLifeCycle(
    onResume: () -> Unit,
    onPause: () -> Unit,
    onStart: () -> Unit,
    viewModel: AddEditViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    // Safely update the current lambdas when a new one is provided
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    Toast.makeText(
        context,
        "DisposableEffectWithLifeCycle composition ENTER",
        Toast.LENGTH_SHORT
    ).show()

    val currentOnResume by rememberUpdatedState(onResume)
    val currentOnPause by rememberUpdatedState(onPause)
    val currentOnStart by rememberUpdatedState(onStart)

    // If `lifecycleOwner` changes, dispose and reset the effect
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for lifecycle events
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {

//                    if (viewModel.coursesList.isEmpty()) {
                        viewModel.addCourses()
                        viewModel.getAllCourse()
//
//                    }
//                    viewModel.getAllCourse()


                }
                Lifecycle.Event.ON_START -> {

                    currentOnStart()

                }
                Lifecycle.Event.ON_RESUME -> {
                    currentOnResume()
                }
                Lifecycle.Event.ON_PAUSE -> {
                    currentOnPause()
                }

                else -> {}
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)

            Toast.makeText(
                context,
                "DisposableEffectWithLifeCycle composition EXIT",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

}
