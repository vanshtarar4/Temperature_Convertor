package com.example.temperatueconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.temperatueconvertor.ui.theme.TemperatueConvertorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemperatueConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val viewModel: ViewModelForTemperatures = viewModel()

    MainScreen(
        isFahrenheit = viewModel.isFahrenheit,
        result = viewModel.convertedTemperatures,
        convertTemp = { viewModel.calculateConversion(it) },
        toggleSwitch = { viewModel.doSwitchToggle() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(isFahrenheit: Boolean, result: String, convertTemp: (String) -> Unit, toggleSwitch: () -> Unit) {
    var inputTextState by remember { mutableStateOf("") }

    fun  onTextChange(newValue: String) {
        inputTextState = newValue
    }
    Column (
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ){
        Text(
            modifier = Modifier.padding(15.dp),
            style = MaterialTheme.typography.headlineMedium,
            text = "Temperature Conversion App",
            color = Color.DarkGray
            )
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 9.dp),
            colors = CardDefaults.cardColors(Color.LightGray),
        ){
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp),

            ) {
                Switch(checked = isFahrenheit, onCheckedChange = { toggleSwitch() })
                OutlinedTextField(
                    value = inputTextState,
                    onValueChange = { onTextChange(it)},
                    label = { Text(text = "Enter temperature",
                        color = Color.DarkGray
                    ) },
                    modifier = Modifier
                        .padding(16.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.DarkGray,
                        fontSize = 22.sp
                    ),
                    trailingIcon = {
                        Text(text = if (isFahrenheit) "\u2109" else "\u2103",
                            color = Color.DarkGray
                        )
                    }
                )
            }
        }
        Text("   Result --> $result",

            Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineMedium,
            color = Color.DarkGray
            )
        Button(onClick = { convertTemp(inputTextState) }) {
            if (isFahrenheit) {
                Text(text = "Convert to Celsius")
            }else {
                Text(text = "Convert to Fahrenheit")
            }
        }
    }

}

