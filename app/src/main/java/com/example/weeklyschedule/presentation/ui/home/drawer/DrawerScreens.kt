package com.example.weeklyschedule.presentation.ui.home.drawer

sealed class DrawerScreens(val title: String) {
    object Home : DrawerScreens("Home")
    object AddEdit : DrawerScreens("AddEditSchedule")
    object Help : DrawerScreens( "Help")
}

val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.AddEdit,
    DrawerScreens.Help
)