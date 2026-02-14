package com.example.jcroom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcroom.room.entities.Insect
import com.example.jcroom.ui.components.ProgressFullScreen
import com.example.jcroom.ui.theme.JCRoomTheme

@Preview(showBackground = true)
@Composable
fun MainViewPreview(){
    JCRoomTheme {
        MainView(
            modifier = Modifier,
            insects = getAllInsectsPreview(),
            false,
            {},
            false,
            {}
        )
    }
}

@Composable
fun MainView(
    modifier: Modifier,
    insects: List<Insect>,
    inProgress: Boolean = false,
    onFilter: (Boolean) -> Unit,
    initFilter: Boolean,
    onLongClick: (Insect) -> Unit
    ){

    var selectChip by remember { mutableStateOf(initFilter) }

    Box(
        modifier = modifier.padding(horizontal = dimensionResource(R.dimen.common_padding_min))
    ){
        Column {
            InputChip(
                selected = selectChip,
                label = {
                    Text("Mis insectos")
                },
                onClick = {
                    selectChip = !selectChip
                    onFilter(selectChip)
                },
                avatar = {
                    if (selectChip){
                        Icon(
                            Icons.Default.Done,
                            contentDescription = null,
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }else{
                        null
                    }
                }
            )
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(minSize = dimensionResource(R.dimen.main_grid_min_size)),
                verticalItemSpacing = dimensionResource(R.dimen.common_padding_min),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.common_padding_min)),
                content = {
                    items(insects){ insect ->
                        ItemInsectView(
                            insect,
                            modifier = Modifier.combinedClickable(
                                onClick = {},
                                onLongClick = {onLongClick(insect)}
                            )
                            )
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        AnimatedVisibility(
            visible = inProgress,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ProgressFullScreen()
        }
    }
}