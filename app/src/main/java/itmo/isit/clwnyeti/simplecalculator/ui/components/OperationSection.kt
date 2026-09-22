package itmo.isit.clwnyeti.simplecalculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import itmo.isit.clwnyeti.simplecalculator.logic.OneNumberOperations
import itmo.isit.clwnyeti.simplecalculator.logic.TwoNumbersOperations

@Composable
fun OperationProcessingSection(
    operationOnRow: Int,
    currentText: MutableState<String>,
    currentValue: MutableState<Double>,
    lastOperation: MutableState<TwoNumbersOperations?>,
    rightValue: MutableState<String>,
    handleOperation: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val operations = TwoNumbersOperations.entries
        val operationsSize = operations.size
        val rowCount = (operationsSize - 1) / operationOnRow + 1
        val lastRowOperationCount = if (operationsSize % operationOnRow == 0) operationOnRow else operationsSize % operationOnRow
        repeat(rowCount - 1) { rowIndex ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                repeat(operationOnRow) { columnIndex ->
                    OperationProcessingButton(
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
                OperationProcessingButton(
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

@Composable
fun OperationResultSection(
    operationOnRow: Int,
    currentText: MutableState<String>,
    currentValue: MutableState<Double>,
    lastOperation: MutableState<TwoNumbersOperations?>,
    rightValue: MutableState<String>,
    handleOperation: (OneNumberOperations) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val operations = OneNumberOperations.entries
        val operationsSize = operations.size
        val rowCount = (operationsSize - 1) / operationOnRow + 1
        val lastRowOperationCount = if (operationsSize % operationOnRow == 0) operationOnRow else operationsSize % operationOnRow
        repeat(rowCount - 1) { rowIndex ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                repeat(operationOnRow) { columnIndex ->
                    OperationResultButton(
                        operations[rowIndex * operationOnRow + columnIndex],
                        currentText,
                        currentValue,
                        lastOperation,
                        rightValue,
                        handleOperation,
                    )
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            repeat(lastRowOperationCount) { columnIndex ->
                OperationResultButton(
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