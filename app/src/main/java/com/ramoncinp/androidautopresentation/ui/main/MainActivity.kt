package com.ramoncinp.androidautopresentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramoncinp.androidautopresentation.R
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
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painterResource(id = R.mipmap.ic_launcher_round),
                        stringResource(id = R.string.app_name),
                    )
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.padding(top = 40.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidAutoPresentationTheme {
        MainContent()
    }
}
