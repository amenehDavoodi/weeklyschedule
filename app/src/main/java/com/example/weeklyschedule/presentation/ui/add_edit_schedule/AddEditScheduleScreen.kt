package com.example.weeklyschedule.presentation.ui.add_edit_schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weeklyschedule.presentation.ui.util.Screen

@Composable
fun AddEditScheduleScreen(
    navController: NavController,
    viewModel:AddEditViewModel= hiltViewModel()
)
{
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "افزودن روزهای هفته"
//                    , modifier = Modifier.clickable {
//                    navController.navigate(Screen.HomeScreen.route)
//                }
                )
                Text(text = "افزودن تعداد زنگ ها در یک روز")
            }
        }
    }

}