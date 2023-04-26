package com.example.weeklyschedule.presentation.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.weeklyschedule.domain.HomeScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.persiancalendar.calendar.PersianDate
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeScheduleRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun showTime():String{
//        val calender= PersianDate(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH)
        val calender= Calendar.YEAR.toString()+"/"+Calendar.MONTH.toString()+"/"+Calendar.DAY_OF_MONTH.toString()
        return calender.toString()
    }
}