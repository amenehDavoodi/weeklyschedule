package com.example.weeklyschedule.presentation.ui.util

sealed class Screen(val route: String) {
    object AddEditScreen: Screen("add_edit_screen")
    object WeeklyScheduleScreen: Screen("weekly_schedule_screen")
    object HomeScreen: Screen("home_screen")
}