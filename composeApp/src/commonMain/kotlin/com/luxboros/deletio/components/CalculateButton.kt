package com.luxboros.deletio.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luxboros.deletio.ui.theme.LocalDeletioColors

@Composable
fun CalculateButton(
    onClick: () -> Unit,
    isEnabled: Boolean,
) {
    Button(
        onClick,
        Modifier.fillMaxWidth().height(56.dp),
        isEnabled,
        shape = MaterialTheme.shapes.extraLarge,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledContentColor = LocalDeletioColors.current.textSecondary,
        )
    ) {
        Text("Calculate", style = MaterialTheme.typography.titleMedium)
    }
}