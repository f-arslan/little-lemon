package com.example.littlelemon.common.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R.drawable.logo
import com.example.littlelemon.util.Constants.GREEN_DARK
import com.example.littlelemon.util.Constants.HIGH_PADDING
import com.example.littlelemon.util.Constants.MAX_PADDING
import com.example.littlelemon.util.Constants.VERY_HIGH_PADDING

@Composable
fun LittleLemonTopBar() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = logo),
            contentDescription = "App Logo",
            modifier = Modifier.padding(top = HIGH_PADDING, bottom = HIGH_PADDING)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(GREEN_DARK),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Let's get to know you",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = MAX_PADDING, bottom = MAX_PADDING)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LittleLemonTopBarPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        LittleLemonTopBar()
    }
}