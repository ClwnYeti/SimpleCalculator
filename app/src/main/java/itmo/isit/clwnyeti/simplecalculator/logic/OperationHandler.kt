package itmo.isit.clwnyeti.simplecalculator.logic

import androidx.compose.runtime.MutableState
import kotlin.text.toDouble

fun handleTwoNumberOperations(currentValue: MutableState<Double>, lastOperation: MutableState<TwoNumbersOperations?>, rightValueString: MutableState<String>) {
    val lastOperationValue = lastOperation.value
    val rightValue = rightValueString.value.toDouble()
    currentValue.value = getHandledTwoNumberValue(currentValue.value, lastOperationValue, rightValue)
    rightValueString.value = DefaultValues.INITIAL_RIGHT_VALUE_STRING
}
fun handleOneNumberOperations(currentValue: MutableState<Double>, currentOperation: OneNumberOperations, lastOperation: MutableState<TwoNumbersOperations?>, rightValueString: MutableState<String>) {
    if (currentOperation == OneNumberOperations.Point && lastOperation.value != DefaultValues.InitialOperation && rightValueString.value != DefaultValues.INITIAL_RIGHT_VALUE_STRING) {
        val rightValue = rightValueString.value.toDouble()
        rightValueString.value = getHandledOneNumberValue(rightValue, currentOperation).toString()
        return
    }

    if (rightValueString.value != DefaultValues.INITIAL_RIGHT_VALUE_STRING) {
        handleTwoNumberOperations(currentValue, lastOperation, rightValueString)
    }

    currentValue.value = getHandledOneNumberValue(currentValue.value, currentOperation)
    lastOperation.value = DefaultValues.InitialOperation
    rightValueString.value = DefaultValues.INITIAL_RIGHT_VALUE_STRING
}

fun getHandledTwoNumberValue(currentValue: Double, operation: TwoNumbersOperations?, rightValue: Double): Double {
    return when(operation) {
        TwoNumbersOperations.Plus -> currentValue + rightValue
        TwoNumbersOperations.Minus -> currentValue - rightValue
        TwoNumbersOperations.Divide ->  {
            if (rightValue == DefaultValues.INITIAL_VALUE) {
                throw ArithmeticException("Division by zero")
            }
            return currentValue / rightValue
        }

        TwoNumbersOperations.Multiply -> currentValue * rightValue
        null -> rightValue
    }
}

fun getHandledOneNumberValue(currentValue: Double, operation: OneNumberOperations): Double {
    return when(operation) {
        OneNumberOperations.Equals -> currentValue
        OneNumberOperations.Clear -> DefaultValues.INITIAL_VALUE
        OneNumberOperations.Point -> currentValue / DefaultValues.POINT_DIVIDER_VALUE
    }
}