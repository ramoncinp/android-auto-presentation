package com.ramoncinp.androidautopresentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramoncinp.androidautopresentation.ui.theme.AndroidAutoPresentationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAutoPresentationTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun SetUiColors() {
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colors.primary

    SideEffect {
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = false
        )
    }
}

@Composable
fun MyApp() {
    SetUiColors()
    MainContent()
}

@Composable
fun MainContent() {
    Scaffold {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.primary) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "Hello!")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidAutoPresentationTheme {
        Greeting("Android")
    }
}