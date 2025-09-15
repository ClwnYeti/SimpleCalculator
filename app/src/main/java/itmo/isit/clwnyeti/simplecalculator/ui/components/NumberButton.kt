package itmo.isit.clwnyeti.simplecalculator.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import itmo.isit.clwnyeti.simplecalculator.logic.Operations

@Composable
fun NumberButton(
    number: Int,
    currentText: MutableState<String>,
    rightValueString: MutableState<String>) {
    Button(onClick = {
        rightValueString.value += number.toString()
        currentText.value = rightValueString.value
    }) {
        Text(text = number.toString())
    }
}