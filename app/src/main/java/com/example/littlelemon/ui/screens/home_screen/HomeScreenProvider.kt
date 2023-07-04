package com.example.littlelemon.ui.screens.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.common.composables.HomeTopAppBar

@Composable
fun HomeScreenProvider() {

}

@Composable
fun HomeScreen() {
    Scaffold(topBar = {
        HomeTopAppBar()
    }) {
        Column(modifier = Modifier.padding(it)) {
            HeroSection()
        }
    }
}

@Composable
fun HeroSection() {
    Row {
        Column(modifier = Modifier.weight(0.7f)) {
            Text(text = stringResource(id = R.string.hero_heading))
            Text(text = stringResource(id = R.string.hero_sub_heading))
        }
        Card(modifier = Modifier.weight(0.3f)) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HeroSection()
}