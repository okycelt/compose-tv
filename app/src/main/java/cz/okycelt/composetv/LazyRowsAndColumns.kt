package cz.okycelt.composetv

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.ExperimentalTvFoundationApi
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.itemsIndexed
import androidx.tv.material3.Text
import coil.compose.AsyncImage

const val rowsCount = 20
const val columnsCount = 100

@Composable
fun LazyRowsAndColumns() {
    TvLazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        items(rowsCount) { SampleLazyRow() }
    }
}
@OptIn(ExperimentalTvFoundationApi::class)
@Composable
fun SampleLazyRow() {
    val colors = listOf(Color.Red, Color.Magenta, Color.Green, Color.Yellow, Color.Blue, Color.Cyan)
    val backgroundColors = List(columnsCount) { colors.random() }

    Column {
        Text(text = "Row Title")

        FocusGroup {
            TvLazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                itemsIndexed(items = backgroundColors) { index, backgroundColor ->
                    val itemModifier = if (index == 0) {
                        Modifier.initiallyFocused()
                    } else {
                        Modifier.restorableFocus()
                    }

                    AsyncImage(
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        model = "https://picsum.photos/480/640?random=$index",
                        modifier = itemModifier
                            .background(backgroundColor.copy(alpha = 0.3f))
                            .width(120.dp)
                            .height(160.dp)
                            .drawBorderOnFocus()
                            .clickable { }
                    )
                }
            }
        }
    }
}