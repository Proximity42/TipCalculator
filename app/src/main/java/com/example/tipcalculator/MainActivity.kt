package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                TipCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TipSlider(sliderPosition: Float, onPositionChange: (Float) -> Unit) {
    Slider(
        valueRange = 0f..25f,
        value = sliderPosition,
        onValueChange = {onPositionChange(it)}
    )
}

@Composable
fun TipCalculator(modifier: Modifier = Modifier) {
    var input by remember {mutableStateOf("")}
    var result by remember {mutableStateOf("")}

    var sliderPosition by remember { mutableFloatStateOf(5f) }
    val handlePositionChange = {position: Float ->
        sliderPosition = position
    }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text="Сумма заказа:",
                fontSize = 20.sp
            )

            OutlinedTextField(
                value = input,
                onValueChange = {input = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text="Количество блюд:",
                fontSize = 20.sp
            )

            OutlinedTextField(
                value = input,
                onValueChange = {input = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text="Чаевые:",
            fontSize = 20.sp
        )
        TipSlider(
            sliderPosition = sliderPosition,
            onPositionChange = handlePositionChange
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "0", fontSize = 20.sp)
            Text(text = "25", fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipCalculatorTheme {
        TipCalculator()
    }
}