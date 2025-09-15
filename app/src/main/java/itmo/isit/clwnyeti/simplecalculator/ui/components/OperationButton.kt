package itmo.isit.clwnyeti.simplecalculator.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import itmo.isit.clwnyeti.simplecalculator.logic.DefaultValues
import itmo.isit.clwnyeti.simplecalculator.logic.Operations

@Composable
fun OperationButton(
    operation: Operations,
    currentText: MutableState<String>,
    currentValue: MutableState<Double>,
    lastOperation: MutableState<Operations>,
    rightValue: MutableState<String>,
    handleOperation: () -> Unit
) {
    Button(onClick = {
        try {
            if (rightValue.value != "") {
                handleOperation()
                currentText.value = currentValue.value.toString()
            }
            lastOperation.value = operation
        } catch (ex: Exception) {
            currentText.value = DefaultValues.ErrorText
            currentValue.value = DefaultValues.InitialValue
            lastOperation.value = DefaultValues.InitialOperation
            rightValue.value = DefaultValues.InitialRightValueString
        }

    }) {
        Text(text = operation.toText())
    }
}