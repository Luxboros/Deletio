package com.luxboros.deletio.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luxboros.deletio.domain.ResultCardType

@Composable
fun OutputSection(
    payoffDate: String,
    totalInterestPaid: String,
    showResults: Boolean,
    resultType: ResultCardType,
) {
    AnimatedVisibility(
        showResults,
        enter = fadeIn(animationSpec = tween(durationMillis = 300)),
        exit = fadeOut(animationSpec = tween(durationMillis = 300))
    ) {
        Column {
            if (resultType == ResultCardType.WARNING) {
                ErrorCard(payoffDate, totalInterestPaid)
            } else {
                ResultCard(
                    payoffDate,
                    totalInterestPaid,
                    Modifier.padding(16.dp)
                )
            }
        }
    }
}

