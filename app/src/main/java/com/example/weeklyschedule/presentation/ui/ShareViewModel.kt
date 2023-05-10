package com.example.weeklyschedule.presentation.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.weeklyschedule.domain.HomeScheduleRepository
import com.example.weeklyschedule.domain.WeeklyScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val repositoryWeekly: WeeklyScheduleRepository,
    private val repositoryHome: HomeScheduleRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
}