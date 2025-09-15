package itmo.isit.clwnyeti.simplecalculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import itmo.isit.clwnyeti.simplecalculator.logic.Operations
import itmo.isit.clwnyeti.simplecalculator.ui.theme.Pink40

@Composable
fun NumberSection(
    rowCount: Int,
    columnCount: Int,
    startNumber: Int = 0,
    currentText: MutableState<String>,
    rightValue: MutableState<String>,
) {
    Column(
        modifier = Modifier.background(Pink40),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(rowCount) { rowIndex ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                repeat(columnCount) { columnIndex ->
                    NumberButton(
                        startNumber + rowIndex * columnCount + columnIndex,
                        currentText,
                        rightValue
                    )
                }
            }
        }
    }
}

