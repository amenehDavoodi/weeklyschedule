package com.example.weeklyschedule.presentation.ui.add_edit_schedule.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DropDownSelection(listContents: List<String>, label: String,selectedString: (String) -> Unit) {

    val text = remember { mutableStateOf(if (listContents.isNotEmpty())listContents[0] else "") } // initial value
    val isOpen = remember { mutableStateOf(false) } // initial value
    val openCloseOfDropDownList: (Boolean) -> Unit = {
        isOpen.value = it
    }
    val userSelectedString: (String) -> Unit = {
        text.value = it
    }
    val icon = if (isOpen.value)
        Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown

    Box {
        Column {
            OutlinedTextField(
                value = text.value ,
                onValueChange = { text.value = it },
                label = { Text(text = label) },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { isOpen.value = true })
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)

                ,readOnly = true
                ,singleLine = true

            )
            DropDownList(
                requestToOpen = isOpen.value,
                list = listContents,
                openCloseOfDropDownList,
                userSelectedString,
                selectedString
            )
        }
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable(
                    onClick = { isOpen.value = true }
                )
        )
    }
}
@Composable
fun DropDownList(
    requestToOpen: Boolean = false,
    list: List<String>,
    request: (Boolean) -> Unit,
    selectedString: (String) -> Unit,
    userSelectedString: (String) -> Unit
) {
    DropdownMenu(
        modifier = Modifier
            .padding(10.dp)
            .background(Color.Transparent),
//        toggle = {
//            // Implement your toggle
//        },
        expanded = requestToOpen,
        onDismissRequest = { request(false) },
    ) {
        list.forEach {
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    request(false)
                    selectedString(it)
                    userSelectedString(it)
                }
            ) {
                Text(it, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}