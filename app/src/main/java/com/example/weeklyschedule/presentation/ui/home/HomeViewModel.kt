package com.example.weeklyschedule.presentation.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weeklyschedule.data.local.entities.Courses
import com.example.weeklyschedule.domain.HomeScheduleRepository
import com.example.weeklyschedule.presentation.ui.dateUtils.Utilities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeScheduleRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _cList = mutableListOf<Courses>()
    var coursesList: List<Courses> = _cList

    val courseOfADay = repository.getTodayCourses(1)

    fun showTime(): String {
        val persianDate = Utilities().currentShamsidate
        val date = System.currentTimeMillis()
        return persianDate
    }

//    init {
//        getAllCourse()
//    }

    private fun getAllCourse() {

        viewModelScope.launch {

            repository.getAllCourse().collect {
                _cList= it as MutableList<Courses>


            }

        }

    }
    private fun getAllWeek() {

        viewModelScope.launch {

            repository.getAllWeek().collect {
                _cList= it as MutableList<Courses>


            }

        }

    }


}
