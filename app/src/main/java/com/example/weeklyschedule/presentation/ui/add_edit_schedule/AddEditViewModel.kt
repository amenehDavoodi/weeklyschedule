package com.example.weeklyschedule.presentation.ui.add_edit_schedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.weeklyschedule.data.local.DaysList
import com.example.weeklyschedule.domain.WeeklyScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val repository: WeeklyScheduleRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel()
{
    private val _dayList = mutableStateOf(DaysList)
    val dayList: State<List<String>> = _dayList

}