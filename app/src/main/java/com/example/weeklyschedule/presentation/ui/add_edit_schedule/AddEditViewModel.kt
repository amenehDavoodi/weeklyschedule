package com.example.weeklyschedule.presentation.ui.add_edit_schedule

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weeklyschedule.data.local.CourseList
import com.example.weeklyschedule.data.local.DaysList
import com.example.weeklyschedule.data.local.entities.Courses
import com.example.weeklyschedule.domain.WeeklyScheduleRepository
import com.example.weeklyschedule.presentation.ui.general.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
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


    var breaksOfADay by mutableStateOf("")
        private set

    private var _breakList = mutableListOf<String>()
    var breakList: List<String> = _breakList

    init {
        getAllCourse()
    }


    val breaksOfADayHasError: StateFlow<Boolean> =
        snapshotFlow { breaksOfADay }
            .mapLatest { it.length<=1 }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false
            )
    fun updateDropDownBreaks(input: String) {
        viewModelScope.launch {

            breaksOfADay = input
            addBreaksToList()
        }
    }
    private fun addBreaksToList()
    {
        for (i in 1..breaksOfADay.toInt())
        {
            _breakList.add("زنگ  $i")
        }
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