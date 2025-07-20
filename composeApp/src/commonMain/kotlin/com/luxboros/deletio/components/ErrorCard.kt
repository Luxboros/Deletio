package com.luxboros.deletio.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.luxboros.deletio.ui.theme.CustomTheme
import deletio.composeapp.generated.resources.Res
import deletio.composeapp.generated.resources.warning_icon_48px
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * A card that displays a calculation error message.
 * It uses the theme's error colors to provide clear visual feedback.
 *
 * @param title The main error message to display.
 * @param subtitle A more detailed explanation of the error.
 * @param modifier The modifier to be applied to the card.
 */
@Composable
fun ErrorCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(
                    Res.drawable.warning_icon_48px,
                ),
                contentDescription = "Error Icon",
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.error
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onErrorContainer,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun ErrorCardPreviewDark() {
    CustomTheme(darkTheme = true) {
        ErrorCard(
            title = "Invalid Input",
            subtitle = "Can't calculate emptiness. Fill in all fields.",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun ErrorCardPreviewLight() {
    CustomTheme(darkTheme = false) {
        ErrorCard(
            title = "Invalid Input",
            subtitle = "Can't calculate emptiness. Fill in all fields.",
        )
    }
}