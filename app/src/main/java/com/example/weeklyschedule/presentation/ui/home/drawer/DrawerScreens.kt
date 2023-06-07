package com.example.weeklyschedule.presentation.ui.home.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DrawerScreens(val title: String,val icon: ImageVector) {
    object Home : DrawerScreens("خانه",Icons.Filled.Home)
    object AddEdit : DrawerScreens("مدیریت برنامه هفتگی",Icons.Filled.ManageAccounts)
    object Help : DrawerScreens("راهنما",Icons.Filled.Help)
    object Setting : DrawerScreens("تنظیمات",Icons.Filled.Settings)
}

val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.AddEdit,
    DrawerScreens.Help,
    DrawerScreens.Setting
)