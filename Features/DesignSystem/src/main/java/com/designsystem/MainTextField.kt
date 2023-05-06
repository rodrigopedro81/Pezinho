package com.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.designsystem.theme.PezinhoTheme
import com.designsystem.theme.errorColor

@Preview
@Composable
fun MainEditTextPreview() {
    PezinhoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val (text, setText) = remember { mutableStateOf("") }
            MainEditText(text = text, onTextChange = setText)
        }
    }
}

@Composable
fun MainEditText(
    modifier: Modifier = Modifier,
    text:String = "",
    isValid: Boolean = false,
    label: String? = null,
    hint: String? = null,
    startIcon: Int? = null,
    endIcon: Int? = null,
    onTextChange: (String) -> Unit
) {
    val shape = RoundedCornerShape(30.dp)
    val defaultColor = MaterialTheme.colors.secondary
    val currentColor = if (isValid || text.isEmpty()) defaultColor else errorColor
    Column(modifier) {
        label?.let { Label(it, currentColor) }
        Spacer(modifier = Modifier.height(4.dp))
        BasicTextField(
            modifier = Modifier
                .clip(shape)
                .background(Color.White)
                .border(1.dp, currentColor, shape)
                .height(44.dp)
                .fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange.invoke(it) },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                color = currentColor,
                fontSize = 16.sp
            )
        ) { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                startIcon?.let {
                    Icon(painterResource(id = it), null, tint = currentColor)
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Box {
                    hint?.let {
                        if (text.isEmpty()) {
                            Hint(hint = it)
                        }
                    }
                    innerTextField()
                }
                endIcon?.let {
                    Icon(painterResource(it), null, tint = currentColor)
                }
            }
        }
    }
}

@Composable
fun Label(text: String, color: Color) {
    Text(
        modifier = Modifier.offset(25.dp),
        text = text,
        color = color,
        style = TextStyle(fontSize = 12.sp)
    )
}

@Composable
fun Hint(hint: String) {
    Text(
        text = hint,
        style = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onBackground,
            fontSize = 16.sp
        )
    )
}
