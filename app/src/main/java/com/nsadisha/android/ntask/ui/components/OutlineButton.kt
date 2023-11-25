package com.nsadisha.android.ntask.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.shape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nsadisha.android.ntask.R

@Composable
fun OutlineButton(
    label: String,
    onclick: () -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    borderColor: Color = MaterialTheme.colorScheme.primary
) {
    OutlinedButton(
        onClick = onclick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor,
        ),
        border = BorderStroke(2.dp, borderColor),
        modifier = modifier,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = contentColor,
        )
    }
}
