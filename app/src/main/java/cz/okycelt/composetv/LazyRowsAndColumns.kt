package cz.okycelt.composetv

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
const val rowsCount = 20
const val columnsCount = 100
@Composable
fun LazyRowsAndColumns() {
    TvLazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        items(rowsCount) { SampleLazyRow() }
    }
}
@Composable
fun SampleLazyRow() {
    val colors = listOf(Color.Red, Color.Magenta, Color.Green, Color.Yellow, Color.Blue, Color.Cyan)
    val backgroundColors = List(columnsCount) { colors.random() }
    TvLazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        backgroundColors.forEach { backgroundColor ->
            item {
                Box(
                    modifier = Modifier
                        .background(backgroundColor.copy(alpha = 0.3f))
                        .width(200.dp)
                        .height(150.dp)
                        .drawBorderOnFocus()
                        .focusable()
                )
            }
        }
    }
}