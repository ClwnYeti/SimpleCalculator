package itmo.isit.clwnyeti.simplecalculator.logic

import androidx.compose.runtime.MutableState

fun handleOperations(currentValue: MutableState<Double>, lastOperation: MutableState<Operations>, rightValueString: MutableState<String>) {
    val rightValue = rightValueString.value.toInt()
    currentValue.value = getHandledValue(currentValue.value, lastOperation.value, rightValue)
    rightValueString.value = ""
}

fun getHandledValue(currentValue: Double, operation: Operations, rightValue: Int): Double {
    return when(operation) {
        Operations.Plus -> currentValue + rightValue
        Operations.Minus -> currentValue - rightValue
        Operations.Divide ->  {
            if (rightValue == 0) {
                throw ArithmeticException("Division by zero")
            }
            return currentValue / rightValue
        }

        Operations.Multiply -> currentValue * rightValue
    }
}