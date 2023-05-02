 package com.example.weeklyschedule.presentation.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.weeklyschedule.domain.HomeScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.persiancalendar.calendar.PersianDate
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeScheduleRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun showTime(): String {
        val sdf = SimpleDateFormat("'Date\n'dd-MM-yyyy '\n\nand\n\nTime\n'HH:mm:ss")
        val currentDateAndTime = sdf.format(Date())
        val cal=PersianDate(2023,5,24).year.toString()+"/"
        return cal
    }
}