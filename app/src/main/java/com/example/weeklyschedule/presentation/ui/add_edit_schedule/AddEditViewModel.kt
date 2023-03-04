package com.example.weeklyschedule.presentation.ui.add_edit_schedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weeklyschedule.data.local.CourseList
import com.example.weeklyschedule.data.local.DaysList
import com.example.weeklyschedule.data.local.entities.Courses
import com.example.weeklyschedule.domain.WeeklyScheduleRepository
import com.example.weeklyschedule.presentation.ui.general.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val repository: WeeklyScheduleRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dayList = mutableStateOf(DaysList)
    val dayList: State<List<String>> = _dayList


    private var _cList = mutableListOf<String>()
    var courseList: List<String> = _cList

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getAllCourse()
    }

    fun addNewCourse(id: Int, courseName: String) =
        viewModelScope.launch {
            try {
                repository.insertCourse(Courses(id, courseName))
                _eventFlow.emit(UiEvent.SaveWeeklySchedule)
            } catch (e: Exception) {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar(
                        message = e.message ?: "Couldn't save course!"
                    )
                )
            }
        }

    operator fun invoke(): Flow<List<Courses>> {
        return repository.getAllCourse()
    }

    private fun getAllCourse() {


        viewModelScope.launch {
             repository.getAllCourse().collect()
            {

                val test=it[0].courseName
                if (it.isNotEmpty())
                {
                    for (i in 0 until (it.size)){
                        _cList.add(it[i].courseName)

                    }

                }

            }
        }

    }

    private fun addCourses()
    {
        for (i in 1 until (CourseList.size)) {
addNewCourse(i, CourseList[i])
        }
    }

}