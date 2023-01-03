package com.example.weeklyschedule.presentation.ui.add_edit_schedule

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.weeklyschedule.domain.WeeklyScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val repository: WeeklyScheduleRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel()
{

}