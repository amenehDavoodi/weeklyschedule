package com.example.weeklyschedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weeklyschedule.presentation.ui.add_edit_schedule.AddEditScheduleScreen
import com.example.weeklyschedule.presentation.ui.home.HomeScreen
import com.example.weeklyschedule.presentation.ui.theme.WeeklyScheduleTheme
import com.example.weeklyschedule.presentation.ui.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

                WeeklyScheduleTheme {
                    Surface(
                        color = MaterialTheme.colors.background,

                        ) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.HomeScreen.route
                        ) {
                            composable(route = Screen.AddEditScreen.route) {
                                AddEditScheduleScreen(navController = navController)
                            }
                            composable(route = Screen.HomeScreen.route) {
                                HomeScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun DefaultPreview() {
    WeeklyScheduleTheme {
        Greeting("Android")
    }
}