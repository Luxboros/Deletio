package com.luxboros.deletio.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.luxboros.deletio.ui.theme.LocalDeletioColors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Input(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    isError: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.labelSmall,
            color = if (isError) LocalDeletioColors.current.warningLabel else LocalDeletioColors.current.textSecondary,
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = isError,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.3f
                ),
                errorIndicatorColor = LocalDeletioColors.current.warningContent,
                errorCursorColor = LocalDeletioColors.current.warningContent,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

    }
}
