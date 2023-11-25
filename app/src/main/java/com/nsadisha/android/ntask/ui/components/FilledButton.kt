package com.nsadisha.android.ntask.ui.components

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FilledButton(
    label: String,
    onclick: () -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.background
) {
    Button(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = modifier
    ){
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = contentColor,
        )
    }
}