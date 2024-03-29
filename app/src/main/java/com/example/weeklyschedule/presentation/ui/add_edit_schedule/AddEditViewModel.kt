package com.example.weeklyschedule.presentation.ui.add_edit_schedule

import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.example.weeklyschedule.data.local.CourseList
import com.example.weeklyschedule.data.local.CoursePicList
import com.example.weeklyschedule.data.local.DaysList
import com.example.weeklyschedule.data.local.entities.Courses
import com.example.weeklyschedule.data.local.entities.WeeklySchedule
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


    private var _cList = mutableListOf<Courses>()
    var coursesList: List<Courses> = _cList

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
            .mapLatest { it.length <= 1 }
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

    private fun addBreaksToList() {
        for (i in 1..breaksOfADay.toInt()) {
            _breakList.add("زنگ  $i")
        }
    }

    private fun addNewCourse(id: Int, courseName: String,coursePic:Int) =
        viewModelScope.launch {
            try {
                repository.insertCourse(Courses(id, courseName,coursePic))
                _eventFlow.emit(UiEvent.SaveWeeklySchedule)
            } catch (e: Exception) {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar(
                        message = e.message ?: "Couldn't save course!"
                    )
                )
            }
        }

//    operator fun invoke(): Flow<List<Courses>> {
//        return repository.getAllCourse()
//    }

    fun getAllCourse() {
        viewModelScope.launch {
            repository.getAllCourse().collect() {
                if (_cList.isEmpty())
                    _cList.addAll(it)

            }

        }

    }

    fun addCourses() {
        for (i in 1 until (CourseList.size)) {
            addNewCourse(i, CourseList[i],CoursePicList[i])
        }
    }

    fun addNewScheduleForADay(weeklySchedule: WeeklySchedule) {
        viewModelScope.launch {
            try {
                repository.insert(weeklySchedule)
                _eventFlow.emit(UiEvent.SaveWeeklySchedule)
            } catch (e: Exception) {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar(
                        message = e.message ?: "Couldn't save schedule!"
                    )
                )
            }
        }


    }

    fun getListNames(list: List<Any>): List<String> {
        val listCourses = list.filterIsInstance<Courses>()

        val items = arrayListOf<String>()
        for (item in listCourses) {
            items.add(item.courseName)
        }
        return items
    }
    fun getSelectedId(name:String,list:List<Any>):Int
    {

        for (i in 0..list.size)
        {
            if (list.contains(name.trim()))
                return list.indexOf(name)
        }
        return 0

    }
    fun getSelectedCourseId(name:String,list:List<Courses>):Int
    {

        for (i in 0..list.size)
        {
            if (list[i].courseName == name)
                return i
        }
        return 0

    }

}