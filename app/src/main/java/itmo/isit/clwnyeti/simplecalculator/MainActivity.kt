package itmo.isit.clwnyeti.simplecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import itmo.isit.clwnyeti.simplecalculator.ui.theme.SimpleCalculatorTheme
import itmo.isit.clwnyeti.simplecalculator.logic.DefaultValues
import itmo.isit.clwnyeti.simplecalculator.logic.handleOneNumberOperations
import itmo.isit.clwnyeti.simplecalculator.logic.handleTwoNumberOperations
import itmo.isit.clwnyeti.simplecalculator.ui.components.NumberSection
import itmo.isit.clwnyeti.simplecalculator.ui.components.OperationProcessingSection
import itmo.isit.clwnyeti.simplecalculator.ui.components.OperationResultSection
import itmo.isit.clwnyeti.simplecalculator.ui.theme.PurpleGrey40
import itmo.isit.clwnyeti.simplecalculator.ui.theme.PurpleGrey80

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentText = rememberSaveable { mutableStateOf(DefaultValues.INITIAL_TEXT) }
            val currentValue = rememberSaveable { mutableDoubleStateOf(DefaultValues.INITIAL_VALUE) }
            val lastOperation = rememberSaveable { mutableStateOf(DefaultValues.InitialOperation) }
            val rightValue = rememberSaveable { mutableStateOf(DefaultValues.INITIAL_RIGHT_VALUE_STRING) }

            SimpleCalculatorTheme{
                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PurpleGrey80)
                        .semantics {
                            testTagsAsResourceId = true
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 0.dp)
                            .background(PurpleGrey40)
                            .testTag("result"),
                        text = currentText.value
                    )
                    OperationProcessingSection(4, currentText, currentValue, lastOperation, rightValue)
                    {
                        handleTwoNumberOperations(currentValue, lastOperation, rightValue)
                    }
                    OperationResultSection(3, currentText, currentValue, lastOperation, rightValue)
                    {
                        currentOperation -> handleOneNumberOperations(currentValue, currentOperation, lastOperation, rightValue)
                    }
                    NumberSection(2, 5, 0, currentText, rightValue)
                }
            }
        }
    }
}