package com.example.littlelemon.ui.screens.home_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.R
import com.example.littlelemon.RequestState
import com.example.littlelemon.common.composables.HomeTopAppBar
import com.example.littlelemon.common.composables.SearchTextField
import com.example.littlelemon.core.data.Menu
import com.example.littlelemon.core.data.MenuNetwork
import com.example.littlelemon.util.Constants.DARK_GREY
import com.example.littlelemon.util.Constants.GREEN_DARK
import com.example.littlelemon.util.Constants.GREY
import com.example.littlelemon.util.Constants.HIGH_PADDING
import com.example.littlelemon.util.Constants.MEDIUM_PADDING
import com.example.littlelemon.util.Constants.SMALL_MEDIUM_PADDING
import com.example.littlelemon.util.Constants.SMALL_PADDING
import com.example.littlelemon.util.Constants.VERY_HIGH_PADDING
import com.example.littlelemon.util.Constants.YELLOW

@Composable
fun HomeScreenProvider(viewModel: HomeScreenViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getAllDishes()
    }
    val phrase by viewModel.phrase.collectAsStateWithLifecycle()
    val allDishesWithPhrase by viewModel.allDishesWithPhrase.collectAsStateWithLifecycle()

    val selectedMenu by viewModel.selectedMenu.collectAsStateWithLifecycle()

    HomeScreen(
        phrase = phrase,
        dishes = allDishesWithPhrase,
        onPhraseChange = viewModel::onPhraseChanged,
        onMenuChange = viewModel::onMenuChanged,
        selectedMenu = selectedMenu
    )
}

@Composable
fun HomeScreen(
    phrase: String,
    dishes: List<MenuNetwork>,
    onPhraseChange: (String) -> Unit,
    onMenuChange: (Menu) -> Unit,
    selectedMenu: Menu?
) {
    Scaffold(topBar = {
        HomeTopAppBar()
    }) {
        Column(modifier = Modifier.padding(it)) {
            HeroSection(phrase, onPhraseChange)
            MenuBreakDown(onMenuChange, selectedMenu)
            MenuItemList(dishes)
        }
    }
}

@Composable
fun MenuItemList(allDishes: List<MenuNetwork>) {
    LazyColumn {
        items(allDishes) {
            MenuItem(
                title = it.title,
                subtitle = it.description,
                price = it.price,
                imageUrl = it.image
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(title: String, subtitle: String, price: String, imageUrl: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SMALL_PADDING),
        shape = RoundedCornerShape(MEDIUM_PADDING),
        shadowElevation = SMALL_PADDING
    ) {
        Column(modifier = Modifier.padding(SMALL_MEDIUM_PADDING)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier.weight(0.6f)) {
                    Text(text = subtitle, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    Spacer(modifier = Modifier.height(MEDIUM_PADDING))
                    Text(text = "$$price", fontWeight = FontWeight.SemiBold)
                }
                GlideImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier.weight(0.3f)
                )
            }
        }
    }
}

@Composable
fun MenuBreakDown(onMenuItemChange: (Menu) -> Unit, selectedMenu: Menu?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MEDIUM_PADDING)
    ) {
        Text(
            text = stringResource(id = R.string.order_for_delivery),
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(MEDIUM_PADDING))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(SMALL_MEDIUM_PADDING)) {
            items(Menu.values()) {
                Button(
                    onClick = { onMenuItemChange(it) },
                    shape = RoundedCornerShape(MEDIUM_PADDING),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedMenu == it) GREEN_DARK else GREY,
                        contentColor = if (selectedMenu == it) Color.White else DARK_GREY
                    )
                ) {
                    Text(text = it.menuName)
                }
            }
        }
    }
}

@Composable
fun HeroSection(phrase: String, onPhraseChange: (String) -> Unit) {
    Surface(color = GREEN_DARK, contentColor = Color.White) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(
                HIGH_PADDING
            )
        ) {
            Row {
                Column(modifier = Modifier.weight(0.6f)) {
                    Text(
                        text = stringResource(id = R.string.hero_heading),
                        style = MaterialTheme.typography.headlineMedium,
                        color = YELLOW
                    )
                    Text(
                        text = stringResource(id = R.string.hero_sub_heading),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(text = stringResource(id = R.string.hero_subtitle))
                }
                Card(modifier = Modifier.weight(0.3f)) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.height(VERY_HIGH_PADDING))
            SearchTextField(Modifier.fillMaxWidth(), phrase, onPhraseChange)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    MenuItem("Salad", "Great Salad", "$9.99", "")
}