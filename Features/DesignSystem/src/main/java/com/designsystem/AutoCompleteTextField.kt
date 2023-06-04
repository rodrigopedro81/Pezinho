package com.designsystem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AutoCompleteTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    label: String = "",
    isTextValid: Boolean = false,
    autoCompletePredictions: List<String>,
    onSelect: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    Column {
        AnimatedVisibility(
            visible = autoCompletePredictions.isNotEmpty(),
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            LazyColumn(modifier = Modifier.height(60.dp)) {
                items(
                    autoCompletePredictions,
                    itemContent = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable {
                                    onSelect.invoke(it)
                                }
                        ) {
                            Text(it)
                        }
                    }
                )
            }
            Spacer(Modifier.height(16.dp))
        }
        MainEditText(
            modifier = modifier,
            text = text,
            isValid = isTextValid,
            label = label,
            onTextChange = {
                onSearch.invoke(it)
            }
        )
    }
}