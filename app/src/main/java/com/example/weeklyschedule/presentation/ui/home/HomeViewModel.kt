package com.example.weeklyschedule.presentation.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weeklyschedule.data.local.CourseList
import com.example.weeklyschedule.data.local.entities.Courses
import com.example.weeklyschedule.domain.HomeScheduleRepository
import com.example.weeklyschedule.presentation.ui.dateUtils.Utilities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeScheduleRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _cList = mutableListOf<Courses>()
    var coursesList: List<Courses> = _cList
    fun showTime(): String {
        val persianDate = Utilities().currentShamsidate
        val date = System.currentTimeMillis()
        return persianDate
    }

    init {
        getAllCourse()
    }

    private fun getAllCourse() {

        viewModelScope.launch {

            repository.getAllCourse().collect {
                _cList= it as MutableList<Courses>


            }

        }

    }


}
