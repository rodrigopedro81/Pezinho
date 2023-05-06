package com.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.designsystem.theme.PezinhoTheme

@Preview
@Composable
fun MainButtonPreview() {
    PezinhoTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val buttonState = true
            PrimaryMainButton(
                modifier = Modifier.width(200.dp),
                buttonText = "Botão primário",
                isButtonEnabled = buttonState
            ) {

            }
            Spacer(modifier = Modifier.height(32.dp))
            SecondaryMainButton(
                modifier = Modifier.width(200.dp),
                buttonText = "Botão secundário"
            ) {

            }
        }
    }
}

@Composable
fun PrimaryMainButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    isButtonEnabled: Boolean,
    onClick: () -> Unit,
) {
    val disabledColor = MaterialTheme.colors.onBackground
    val roundedShape = RoundedCornerShape(30.dp)
    Button(
        modifier = modifier
            .clip(roundedShape)
            .height(44.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(disabledBackgroundColor = disabledColor),
        enabled = isButtonEnabled
        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = buttonText, style = TextStyle(color = MaterialTheme.colors.onPrimary))
        }
    }
}

@Composable
fun SecondaryMainButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    onClick: () -> Unit,
) {
    val roundedShape = RoundedCornerShape(30.dp)
    Button(
        modifier = modifier
            .clip(roundedShape)
            .border(1.dp, MaterialTheme.colors.secondary, roundedShape)
            .height(44.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = buttonText, style = TextStyle(color = MaterialTheme.colors.secondary))
        }
    }
}
