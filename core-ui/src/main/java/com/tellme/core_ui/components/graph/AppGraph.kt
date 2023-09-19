package com.tellme.core_ui.components.graph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.tellme.core_ui.theme.AppTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Composable
fun <R, C> AppGraph(
    modifier: Modifier = Modifier,
    graphHeader: @Composable () -> Unit,
    filledDividerColor: Color = AppTheme.colors.surface,
    filledCellColor: Color = filledDividerColor,
    rows: ImmutableList<R>,
    columns: ImmutableList<C>,
    rowTitle: @Composable (row: R) -> Unit,
    columnTitle: @Composable (column: C) -> Unit,
    emptyCellContent: @Composable (() -> Unit)? = null,
    markedCells: ImmutableList<Pair<C, R?>> = List(columns.size) { index ->
        Pair(
            columns[index],
            null
        )
    }.toImmutableList(),
    markedCellContent: @Composable (() -> Unit)? = null
) {
    val density = LocalDensity.current

    var cellSize by remember {
        mutableStateOf(IntSize(0, 0))
    }

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        graphHeader()

        for (rowIndex in rows.size downTo 0) {
            Row(
                modifier = Modifier
                    .requiredHeightIn(min = 36.dp)
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                for (columnIndex in 0..columns.size) {

                    if (rowIndex == 0) {
                        if (columnIndex == 0) {
                            AppGraphCell(
                                modifier = Modifier.weight(1f),

                                ) {}
                        } else {
                            AppGraphCell(
                                modifier = Modifier.weight(1f)
                            ) {
                                columnTitle(columns[columnIndex - 1])
                            }
                        }
                    } else {

                        AppGraphCell(
                            modifier = Modifier
                                .weight(1f)
                                .onSizeChanged {
                                    cellSize = it
                                },
                            contentAlignment = if (columnIndex == 0) Alignment.CenterStart else Alignment.Center
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center
                            ) {
                                if (columnIndex == 0) {
                                    rowTitle(rows[rowIndex - 1])
                                } else {
                                    val markedItem = markedCells[columnIndex - 1].second

                                    val markedRowIndex: Int? = if (markedItem == null) {
                                        null
                                    } else {
                                        rows.indexOf(markedItem) + 1
                                    }

                                    val isCellEmpty =
                                        (markedRowIndex == null) || (rowIndex > markedRowIndex)

                                    if (isCellEmpty) {
                                        if (emptyCellContent == null) {
                                            Divider(
                                                modifier = Modifier
                                                    .padding(vertical = 17.dp)
                                                    .requiredHeight(2.dp),
                                                color = AppTheme.colors.onPrimary
                                            )
                                        } else {
                                            emptyCellContent()
                                        }
                                    } else {
                                        Box(modifier = Modifier.fillMaxSize()) {
                                            if (rowIndex == markedRowIndex) {
                                                Column(
                                                    modifier = Modifier
                                                        .align(
                                                            Alignment.BottomCenter
                                                        )
                                                ) {
                                                    Box(
                                                        modifier = Modifier
                                                            .requiredHeightIn(
                                                                min = 18.dp,
                                                                max = (cellSize.height / density.density / 2 + 1).dp
                                                            )
                                                    ) {
                                                        if (markedCellContent == null) {
                                                            Surface(
                                                                modifier = Modifier
                                                                    .fillMaxSize(),
                                                                color = filledCellColor
                                                            ) {}
                                                        } else {
                                                            markedCellContent()
                                                        }
                                                    }
                                                }
                                            } else {
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxHeight()
                                                        .align(
                                                            Alignment.Center
                                                        )
                                                ) {
                                                    if (markedCellContent == null) {
                                                        Surface(
                                                            modifier = Modifier
                                                                .fillMaxSize(),
                                                            color = AppTheme.colors.surface
                                                        ) {}
                                                    } else {
                                                        markedCellContent()
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AppGraphCell(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .background(AppTheme.colors.transparent),
        contentAlignment = contentAlignment
    ) {
        content()
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppGraphPreview() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            AppGraph(
                modifier = Modifier.padding(horizontal = 36.dp),
                graphHeader = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "App Graph Header", style = AppTheme.typography.subtitle1)
                    }
                },
                rows = persistentListOf(1, 2, 3, 4, 5),
                columns = persistentListOf('1', '2', '3', '4', '5', '6', '7'),
                rowTitle = {
                    Text(
                        text = "$it",
                        style = AppTheme.typography.body2,
                        textAlign = TextAlign.Center
                    )
                },
                columnTitle = {
                    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                        Text(
                            text = "$it",
                            style = AppTheme.typography.subtitle1,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                markedCells = persistentListOf(
                    '1' to 3,
                    '2' to 4,
                    '3' to 2,
                    '4' to 4,
                    '5' to 5,
                    '6' to 1,
                    '7' to 3
                ),
                markedCellContent = {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize(),
                        color = AppTheme.colors.surface
                    ) {}
                }
            )
        }
    }
}
