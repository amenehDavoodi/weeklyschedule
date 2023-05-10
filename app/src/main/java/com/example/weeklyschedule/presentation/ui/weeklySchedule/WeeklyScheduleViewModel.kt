package com.example.weeklyschedule.presentation.ui.weeklySchedule

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weeklyschedule.data.local.entities.WeeklySchedule
import com.example.weeklyschedule.domain.WeeklyScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyScheduleViewModel @Inject constructor(
    private val repository: WeeklyScheduleRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    operator fun invoke():Flow<List<WeeklySchedule>>
    {
        return repository.getAllWeek()
    }

//    val allWeek: Flow<List<WeeklySchedule>> = repository.allDaysOfWeek

    fun insert(day: WeeklySchedule) = viewModelScope.launch {
        repository.insert(day)
    }
    fun update(day: WeeklySchedule) = viewModelScope.launch {
        repository.update(day)
    }
    fun delete(id: Int) = viewModelScope.launch {
        repository.deleteADay(id)
    }
}
