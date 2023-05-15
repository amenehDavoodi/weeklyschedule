package com.example.weeklyschedule.presentation.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weeklyschedule.data.local.CourseList
import com.example.weeklyschedule.data.local.entities.Courses
import com.example.weeklyschedule.domain.HomeScheduleRepository
import com.example.weeklyschedule.domain.WeeklyScheduleRepository
import com.example.weeklyschedule.presentation.ui.general.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val repositoryWeekly: WeeklyScheduleRepository,
    private val repositoryHome: HomeScheduleRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


        fun addCourses() {
            for (i in 1 until (CourseList.size)) {
                addNewCourse(i, CourseList[i])
            }
        }
    private fun addNewCourse(id: Int, courseName: String) =
        viewModelScope.launch {
            try {
                repositoryWeekly.insertCourse(Courses(id, courseName))
                _eventFlow.emit(UiEvent.SaveWeeklySchedule)
            } catch (e: Exception) {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar(
                        message = e.message ?: "Couldn't save course!"
                    )
                )
            }
        }

}