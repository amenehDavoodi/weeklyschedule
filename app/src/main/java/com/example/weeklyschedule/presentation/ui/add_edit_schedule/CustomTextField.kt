package com.example.weeklyschedule.presentation.ui.add_edit_schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldWithIcons(
    label: String,
    hint: String,
    imageVector: ImageVector = Icons.Default.DateRange,
    modifier: Modifier = Modifier,
    errorMsg: String = ""
) {

    var text by remember { mutableStateOf(TextFieldValue("")) }

    var isError by rememberSaveable { mutableStateOf(false) }

    fun validate(text: String) {
        isError = text.isEmpty()
    }

    val maxChar = 1
    Column {
        OutlinedTextField(
            modifier = modifier,
            value = text,
            onValueChange = {

                if (it.text.length  <= maxChar) {
                    text = it
                    isError = false
                }
            },
            leadingIcon = { Icon(imageVector = imageVector, contentDescription = "icon") },
            trailingIcon = {
                if (isError)
                    Icon(Icons.Filled.Error, "error", tint = colors.error)
            },
            singleLine = true,
            isError = isError,
            keyboardActions = KeyboardActions { validate(text.toString()) },
            label = { Text(text = label) },
            placeholder = { Text(text = hint) },
            shape = MaterialTheme.shapes.small,
            colors = TextFieldDefaults.outlinedTextFieldColors(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

        )
        if (isError) {
            Text(
                text = errorMsg,
                color = colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }


}
