package com.example.weeklyschedule.presentation.ui.weeklySchedule

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weeklyschedule.data.local.entities.WeeklySchedule
import com.example.weeklyschedule.data.repository.WeeklyScheduleRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyScheduleViewModel @Inject constructor(
    private val repository: WeeklyScheduleRepositoryImp,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val allWeek: Flow<List<WeeklySchedule>> = repository.allDaysOfWeek

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

//class WordViewModelFactory(private val repository: WeeklyScheduleRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(WeeklyScheduleViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return WeeklyScheduleViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}