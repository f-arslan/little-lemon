package com.example.littlelemon.common.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.littlelemon.util.Constants.HIGH_PADDING

@Composable
fun TextFieldWithLabel(
    label: String,
    textFieldText: String,
    onTextChange: (String) -> Unit = {},
    readOnly: Boolean = false
) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        OutlinedTextField(
            value = textFieldText,
            onValueChange = onTextChange,
            modifier = Modifier.fillMaxWidth(),
            readOnly = readOnly
        )
    }
}

@Composable
fun SearchTextField(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit) {
    TextField(
        modifier = modifier,
        value = value,
        placeholder = { Text(text = "Enter search phrase") },
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search") },
        onValueChange = onValueChange,
        shape = RoundedCornerShape(HIGH_PADDING),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )
}

@Composable
@Preview(showBackground = true)
fun TextFieldPreview() {
    SearchTextField(Modifier, "", {})
}