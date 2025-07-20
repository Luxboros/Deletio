package com.luxboros.deletio.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InputSection(
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

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Input(
            value = loanTotal,
            onValueChange = onLoanTotalChange,
            isError = loanTotalError,
            label = "Loan Total",
        )
        Input(
            value = monthlyPayment,
            onValueChange = onMonthlyPaymentChange,
            label = "Monthly Payment",
            isError = monthlyPaymentError,
        )
        Input(
            value = interestRate,
            onValueChange = onInterestRateChange,
            label = "Interest Rate (%)",
            isError = interestRateError,
        )

    }
}