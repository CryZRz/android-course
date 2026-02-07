package com.example.jcadapters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jcadapters.ui.components.ItemListAdvanced
import com.example.jcadapters.ui.components.ItemListBase
import com.example.jcadapters.ui.components.SpinnerCr
import com.example.jcadapters.ui.theme.JCAdaptersTheme

@Preview(showBackground = true)
@Composable
fun MainPreview(){
    JCAdaptersTheme {
        MainView(Modifier.padding(top = 24.dp), {})
    }
}

@Composable
fun MainView(modifier: Modifier, onSelectedItem: (Food) -> Unit){
    val conuntries = listOf<String>("Argertina", "Colombia", "España", "Mexico", "Panama", "Peru")
    val friends = listOf<String>("Victor", "Juanpa", "Jazmin", "Monse", "Diego", "Alan", "Mau")
    val foods = getAllFoods()

    Column(modifier.padding(dimensionResource(R.dimen.common_padding_default))) {
        SpinnerCr(
            Modifier.fillMaxWidth(),
            "Pais",
            conuntries
            )
        /*Text(
            "Friends",
            Modifier.padding(top = dimensionResource(R.dimen.common_padding_default))
        )
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
        ) {
            friends.forEach {friend ->
                ItemListBase(friend)
            }
        }*/

        Text(
            "Comidas",
            Modifier.padding(top = dimensionResource(R.dimen.common_padding_default))
        )
        LazyColumn {
            items(foods.size){ index ->
                val food = foods[index]
                ItemListAdvanced(
                    Modifier.clickable{
                    onSelectedItem(food)
                },
                    food.name,
                    food.description,
                    food.imgUrl,
                    icon = Icons.Default.Favorite,
                    overLineText = food.author,
                    showDivider = true
                    )
            }
        }
    }
}