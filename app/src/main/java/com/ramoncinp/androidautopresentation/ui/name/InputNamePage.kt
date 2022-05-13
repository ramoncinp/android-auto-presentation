package com.ramoncinp.androidautopresentation.ui.name

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramoncinp.androidautopresentation.R
import com.ramoncinp.androidautopresentation.ui.theme.AndroidAutoPresentationTheme
import com.ramoncinp.androidautopresentation.ui.theme.mediumTextSize
import com.ramoncinp.androidautopresentation.ui.theme.normalTextSize
import com.ramoncinp.androidautopresentation.ui.theme.placeHolderStyle

@Composable
fun InputNamePage() {
    Scaffold {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.primary) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputNameText()
                NameInputText()
                SubmitButton()
            }
        }
    }
}

@Composable
fun InputNameText() {
    Text(
        text = "Type your name",
        style = TextStyle(fontSize = mediumTextSize, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(bottom = 24.dp)
    )
}

@Composable
fun NameInputText() {
    var name by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    TextField(
        value = name,
        singleLine = true,
        onValueChange = { name = it },
        label = { Text(stringResource(R.string.name), style = placeHolderStyle) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            focusedLabelColor = MaterialTheme.colors.primary
        ),
        textStyle = LocalTextStyle.current.copy(fontSize = mediumTextSize),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
    )
}

@Composable
fun SubmitButton() {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
        onClick = { },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 56.dp, vertical = 16.dp)
    ) {
        Text(
            "SUBMIT",
            modifier = Modifier.padding(8.dp),
            style = TextStyle(fontSize = normalTextSize)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidAutoPresentationTheme {
        InputNamePage()
    }
}
