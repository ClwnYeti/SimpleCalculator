package itmo.isit.clwnyeti.simplecalculator.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import itmo.isit.clwnyeti.simplecalculator.logic.DefaultValues
import itmo.isit.clwnyeti.simplecalculator.logic.OneNumberOperations
import itmo.isit.clwnyeti.simplecalculator.logic.TwoNumbersOperations

@Composable
fun OperationProcessingButton(
    operation: TwoNumbersOperations,
    currentText: MutableState<String>,
    currentValue: MutableState<Double>,
    lastOperation: MutableState<TwoNumbersOperations?>,
    rightValue: MutableState<String>,
    handleOperation: () -> Unit
) {
    Button(onClick = {
        try {
            if (
                operation == TwoNumbersOperations.Minus &&
                rightValue.value.isEmpty()
            ) {
                rightValue.value = TwoNumbersOperations.Minus.text
                return@Button
            }

            if (rightValue.value != "") {
                handleOperation()
                currentText.value = currentValue.value.toString()
            }
            lastOperation.value = operation
        } catch (_: Exception) {
            currentText.value = DefaultValues.ERROR_TEXT
            currentValue.value = DefaultValues.INITIAL_VALUE
            lastOperation.value = DefaultValues.InitialOperation
            rightValue.value = DefaultValues.INITIAL_RIGHT_VALUE_STRING
        }

    }) {
        Text(text = operation.toText())
    }
}

@Composable
fun OperationResultButton(
    operation: OneNumberOperations,
    currentText: MutableState<String>,
    currentValue: MutableState<Double>,
    lastOperation: MutableState<TwoNumbersOperations?>,
    rightValue: MutableState<String>,
    handleOperation: (OneNumberOperations) -> Unit
) {
    Button(onClick = {
        try {
            handleOperation(operation)
            if (operation == OneNumberOperations.Point && lastOperation.value != DefaultValues.InitialOperation && rightValue.value != DefaultValues.INITIAL_RIGHT_VALUE_STRING) {
                currentText.value = rightValue.value
            }
            else {
                currentText.value = currentValue.value.toString()
            }
        } catch (_: Exception) {
            currentText.value = DefaultValues.ERROR_TEXT
            currentValue.value = DefaultValues.INITIAL_VALUE
        }
    }) {
        Text(text = operation.toText())
    }
}