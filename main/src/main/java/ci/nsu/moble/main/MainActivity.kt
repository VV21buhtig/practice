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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
    "Orange" to Color(0xFFFF9800),
    "Yellow" to Color.Yellow,
    "Green" to Color(0xFF00FF00),
    "Blue" to Color.Blue,
    "Indigo" to Color(0xFF4B0082),
    "Violet" to Color(0xFF9C27B0)
)

@Composable
fun MainScreen() {
    var colorName by remember { mutableStateOf(TextFieldValue("")) }
    var buttonColor by remember { mutableStateOf(Color(0xFF00FF00)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = colorName,
            onValueChange = { colorName = it },
            label = { Text("Color Name") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(0xFFE0E0E0)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFE0E0E0),
                unfocusedContainerColor = Color(0xFFE0E0E0),
                focusedIndicatorColor = Color(0xFF555555),
                unfocusedIndicatorColor = Color(0xFF555555),
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            singleLine = true
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
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
        ) {
            Text("Применить цвет", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

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
            .height(50.dp)
            .background(color, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = name,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp)
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