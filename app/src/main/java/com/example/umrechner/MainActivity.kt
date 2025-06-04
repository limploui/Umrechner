package com.example.umrechner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.umrechner.ui.theme.UmrechnerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UmrechnerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserInputInterface(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UserInputInterface(modifier: Modifier = Modifier) {
    var number by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    val technetium = number.toIntOrNull()?.let { recycler(it) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Zentriert vertikal

    ) {
        TextField(
            value = number,
            onValueChange = { number = it },
            label = { Text("Gib dein Alter ein") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp)) // Abstand zwischen TextField und Button
        Button(
            onClick = {
                if (technetium != null) {
                    output = "Ah, du bist $number Jahre alt, das entspricht $technetium Tagen!"
                } else {
                    output = "Bitte gib eine gültige Zahl ein."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Umrechnen")
        }

        Spacer(modifier = Modifier.height(16.dp)) // Abstand zum Text
        if (output.isNotEmpty()) {
            Text(
                text = output,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserInputInterfacePreview() {
    UmrechnerTheme {
        UserInputInterface()
    }
}

fun recycler(x: Int): Int {
    // Hier kommt deine Logik rein
    var tage: Int = 0


    if (x == 1) {

        tage=366

    }else if (x>1){
        tage = 366

        for (i in 2..x) {
            if (i % 4 == 0) {
                tage += 366
            } else {
                tage += 365
            }
        }

    }else if (x==0){

        tage=0

    }
    return tage
}