package ci.nsu.moble.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ci.nsu.moble.main.ui.theme.PracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                MainScreen()
            }
        }
    }
}

private val colorsMap = mapOf(
    "Red" to Color.Red,
    "Orange" to Color(0xFFFFA500),
    "Yellow" to Color.Yellow,
    "Green" to Color.Green,
    "Blue" to Color.Blue,
    "Indigo" to Color(0xFF4B0082),
    "Violet" to Color(0xFFEE82EE)
)

@Composable
fun MainScreen() {
    var colorName by remember { mutableStateOf(TextFieldValue("")) }
    var buttonColor by remember { mutableStateOf(Color.Gray) }
    var logMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = colorName,
            onValueChange = { colorName = it },
            label = { Text("Color Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val inputColor = colorName.text.trim()
                val foundColor = colorsMap.entries.find { it.key.equals(inputColor, ignoreCase = true) }

                if (foundColor != null) {
                    buttonColor = foundColor.value
                    Log.d("ColorSearch", "Color '$inputColor' found and applied")
                } else {
                    Log.w("ColorSearch", "Color '$inputColor' not found")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
        ) {
            Text("Apply Color")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Color Palette:", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(colorsMap.entries.toList()) { (name, color) ->
                ColorItem(name = name, color = color)
            }
        }
    }
}

@Composable
fun ColorItem(name: String, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            color = if (color == Color.Yellow || color == Color(0xFFFFA500) || color == Color(0xFFEE82EE)) Color.Black else Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    PracticeTheme {
        MainScreen()
    }
}