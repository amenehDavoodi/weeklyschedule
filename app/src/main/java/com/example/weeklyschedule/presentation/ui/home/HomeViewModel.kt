package com.example.weeklyschedule.presentation.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.weeklyschedule.domain.HomeScheduleRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: HomeScheduleRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
}