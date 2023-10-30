package com.example.temperatueconvertor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.lang.Exception

class ViewModelForTemperatures : ViewModel(){
    var isFahrenheit by mutableStateOf(true)

    var convertedTemperatures by mutableStateOf("")

    fun doSwitchToggle() {
        isFahrenheit = !isFahrenheit
    }

    fun calculateConversion(inputValue: String) {

        try {
            val temperature = inputValue.toDouble()

            if (isFahrenheit) {
                convertedTemperatures = "%.2f".format((temperature - 32) * 5/9)
                convertedTemperatures += "\u2103"
            } else {
                convertedTemperatures = "%.2f".format(temperature * 9/5 + 32)
                convertedTemperatures += "\u2109"
            }
        }
        catch (e: Exception) {
            convertedTemperatures = "Invalid Input💀"
        }
    }

}