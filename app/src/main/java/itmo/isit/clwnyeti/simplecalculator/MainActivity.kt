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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import itmo.isit.clwnyeti.simplecalculator.ui.theme.SimpleCalculatorTheme
import itmo.isit.clwnyeti.simplecalculator.logic.DefaultValues
import itmo.isit.clwnyeti.simplecalculator.logic.handleOperations
import itmo.isit.clwnyeti.simplecalculator.ui.components.NumberSection
import itmo.isit.clwnyeti.simplecalculator.ui.components.OperationSection
import itmo.isit.clwnyeti.simplecalculator.ui.theme.PurpleGrey40
import itmo.isit.clwnyeti.simplecalculator.ui.theme.PurpleGrey80

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentText = rememberSaveable { mutableStateOf(DefaultValues.InitialText) }
            val currentValue = rememberSaveable { mutableDoubleStateOf(DefaultValues.InitialValue) }
            val lastOperation = rememberSaveable { mutableStateOf(DefaultValues.InitialOperation) }
            val rightValue = rememberSaveable { mutableStateOf(DefaultValues.InitialRightValueString) }

            SimpleCalculatorTheme {
                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PurpleGrey80)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 0.dp)
                            .background(PurpleGrey40),
                        text = currentText.value
                    )
                    OperationSection(4, currentText, currentValue, lastOperation, rightValue)
                    {
                        handleOperations(currentValue, lastOperation, rightValue)
                    }
                    NumberSection(2, 5, 0, currentText, rightValue)
                }
            }
        }
    }
}