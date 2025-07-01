package com.luxboros.deletio.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun InputCard(
    loanTotal: String,
    loanTotalError: Boolean,
    onLoanTotalChange: (String) -> Unit = {},
    monthlyPayment: String,
    monthlyPaymentError: Boolean,
    onMonthlyPaymentChange: (String) -> Unit = {},
    interestRate: String,
    interestRateError: Boolean,
    onInterestRateChange: (String) -> Unit = {},
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.extraLarge,
        border = BorderStroke(
            1.dp, MaterialTheme.colorScheme.surface.copy(alpha = 0.12f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Input(
                value = loanTotal,
                onValueChange = onLoanTotalChange,
                labelText = "Loan Total",
                isError = loanTotalError,
            )

            Spacer(Modifier.padding(vertical = 8.dp))
            Input(
                value = monthlyPayment,
                onValueChange = onMonthlyPaymentChange,
                labelText = "Monthly Payment",
                isError = monthlyPaymentError,
            )
            Spacer(Modifier.padding(vertical = 8.dp))
            Input(
                value = interestRate,
                onValueChange = onInterestRateChange,
                labelText = "Interest Rate (%)",
                isError = interestRateError,
            )
        }
    }
}