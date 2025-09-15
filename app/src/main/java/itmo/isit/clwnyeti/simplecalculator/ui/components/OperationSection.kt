package itmo.isit.clwnyeti.simplecalculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import itmo.isit.clwnyeti.simplecalculator.logic.Operations

@Composable
fun OperationSection(
    operationOnRow: Int,
    currentText: MutableState<String>,
    currentValue: MutableState<Double>,
    lastOperation: MutableState<Operations>,
    rightValue: MutableState<String>,
    handleOperation: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val operations = Operations.entries
        val operationsSize = operations.size
        val rowCount = (operationsSize - 1) / operationOnRow + 1
        val lastRowOperationCount = if (operationsSize % operationOnRow == 0) operationOnRow else operationsSize % operationOnRow
        repeat(rowCount - 1) { rowIndex ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                repeat(operationOnRow) { columnIndex ->
                    OperationButton(
                        operations[rowIndex * operationOnRow + columnIndex],
                        currentText,
                        currentValue,
                        lastOperation,
                        rightValue,
                        handleOperation
                    )
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            repeat(lastRowOperationCount) { columnIndex ->
                OperationButton(
                    operations[(rowCount - 1) * operationOnRow + columnIndex],
                    currentText,
                    currentValue,
                    lastOperation,
                    rightValue,
                    handleOperation
                )
            }
        }
    }
}