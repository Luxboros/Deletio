package com.luxboros.deletio.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.luxboros.deletio.domain.ResultCardType
import com.luxboros.deletio.ui.theme.LocalDeletioColors

@Composable
fun ResultCard(
    label: String,
    value: String,
    resultType: ResultCardType,
    modifier: Modifier = Modifier.Companion,
) {
    val customColors = LocalDeletioColors.current

    val surfaceColor: Color
    val labelColor: Color
    val valueColor: Color

    when (resultType) {
        ResultCardType.POSITIVE -> {
            surfaceColor = customColors.positiveSurface
            labelColor = customColors.positiveLabel
            valueColor = customColors.positiveContent
        }

        ResultCardType.INFO -> {
            surfaceColor = customColors.infoSurface
            labelColor = customColors.infoLabel
            valueColor = customColors.infoContent
        }

        ResultCardType.WARNING -> {
            surfaceColor = customColors.warningSurface
            labelColor = customColors.warningLabel
            valueColor = customColors.warningContent
        }
    }
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(containerColor = surfaceColor),
        border = BorderStroke(
            1.dp, color = valueColor.copy(alpha = 0.3f),
        )
    ) {
        Column(
            modifier = Modifier.Companion.padding(16.dp, 12.dp),
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            Text(
                label,
                style = MaterialTheme.typography.labelMedium,
                color = labelColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                value,
                style = MaterialTheme.typography.headlineSmall,
                color = valueColor,
                fontWeight = FontWeight.Companion.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}