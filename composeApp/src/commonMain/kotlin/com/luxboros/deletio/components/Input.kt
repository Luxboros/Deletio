package com.luxboros.deletio.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.luxboros.deletio.ui.theme.CustomTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * A custom styled OutlinedTextField for the application.
 * It provides a consistent look and feel for all text inputs.
 *
 * @param value The input text to be shown in the text field.
 * @param onValueChange The callback that is triggered when the input service updates the text.
 * @param label The label to be displayed inside the text field container.
 * @param modifier The modifier to be applied to the text field.
 * @param keyboardType The keyboard type to be used for this text field. Defaults to Number.
 * @param isError Indicates if the text field's current value is in an error state.
 * If true, the label, border, and cursor will be colored to reflect the error.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    keyboardType: KeyboardType = KeyboardType.Decimal,
    isError: Boolean = false,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelLarge,
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            isError = isError,
            shape = MaterialTheme.shapes.large,
            colors = OutlinedTextFieldDefaults.colors(
                errorContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                cursorColor = MaterialTheme.colorScheme.primary,

                ),
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true
        )
    }
}

@Preview
@Composable
fun InputPreviewDark() {
    CustomTheme(darkTheme = true) {
        Input(
            value = "12345",
            onValueChange = { },
            label = "Label",
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Decimal,
            isError = false
        )
    }
}

@Preview
@Composable
fun InputPreviewLight() {
    CustomTheme(darkTheme = false) {
        Input(
            value = "12345",
            onValueChange = { },
            label = "Label",
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Decimal,
            isError = false
        )
    }
}
