package com.luxboros.deletio.components

//@Composable
//fun CalculateButton(
//    onClick: () -> Unit,
//    isEnabled: Boolean,
//) {
//    Button(
//        onClick,
//        Modifier.fillMaxWidth().height(56.dp),
//        isEnabled,
//        shape = MaterialTheme.shapes.extraLarge,
//        colors = ButtonDefaults.buttonColors(
//            containerColor = MaterialTheme.colorScheme.primary,
//            contentColor = MaterialTheme.colorScheme.onPrimary,
//            disabledContainerColor = MaterialTheme.colorScheme.surface,
//            disabledContentColor = LocalDeletioColors.current.textSecondary,
//        )
//    ) {
//        Text("Calculate", style = MaterialTheme.typography.titleMedium)
//    }
//}
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A custom styled primary call-to-action button for the application.
 * It uses the primary color from the MaterialTheme for its background.
 *
 * @param text The text to display on the button.
 * @param onClick The lambda to be executed when the button is clicked.
 * @param enabled Controls the enabled state of the button. When false, the button will not be clickable and will be displayed in a disabled state.
 * @param modifier The modifier to be applied to the button.
 */
@Composable
fun PrimaryActionButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(60.dp),
        shape = RoundedCornerShape(16.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp, pressedElevation = 2.dp
        )
    ) {
        Text(
            text = text, style = MaterialTheme.typography.labelLarge
        )
    }
}
