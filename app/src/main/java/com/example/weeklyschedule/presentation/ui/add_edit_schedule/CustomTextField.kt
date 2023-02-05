package com.example.weeklyschedule.presentation.ui.add_edit_schedule

import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun TextFieldWithIcons( label:String,hint :String, imageVector: ImageVector =Icons.Default.DateRange,modifier: Modifier=Modifier) {

    var text by remember { mutableStateOf(TextFieldValue("")) }
    return OutlinedTextField(
        value = text
        ,
        modifier = modifier
            , singleLine = true,
        leadingIcon = { Icon(imageVector = imageVector, contentDescription = "icon") },
        onValueChange = {
            text = it
        },
        label = { Text(text = label) },
        placeholder = { Text(text = hint) },

    )
}