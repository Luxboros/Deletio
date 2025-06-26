package com.luxboros.deletio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var loanTotal by remember { mutableStateOf("") }
        var monthlyPayment by remember { mutableStateOf("") }
        var interestRate by remember { mutableStateOf("") }
        var payoffDate by remember { mutableStateOf("0") }
        var totalInterestPaid by remember { mutableStateOf("0") }
        var loanTotalError by remember { mutableStateOf(false) }
        var monthlyPaymentError by remember { mutableStateOf(false) }
        var interestRateError by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier.safeContentPadding().fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            OutlinedTextField(
                value = loanTotal,
                onValueChange = { it ->
                    loanTotal = it
                    loanTotalError =
                        it.contains(Regex("[^0-9]")) || it.isEmpty()
                },
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                placeholder = { Text("10000") },
                isError = loanTotalError,
                label = {
                    Text("Loan Total")
                })
            OutlinedTextField(
                shape = MaterialTheme.shapes.medium,
                singleLine = true,
                value = monthlyPayment,
                onValueChange = { it ->
                    monthlyPayment = it
                    monthlyPaymentError =
                        it.contains(Regex("[^0-9]")) || it.isEmpty()
                },
                placeholder = { Text("1000") },
                isError = monthlyPaymentError,
                label = { Text("Monthly Payment") })
            OutlinedTextField(
                shape = MaterialTheme.shapes.medium,
                singleLine = true,
                value = interestRate,
                onValueChange = { it ->
                    interestRate = it
                    interestRateError =
                        it.contains(Regex("[^0-9]")) || it.isEmpty()
                },
                placeholder = { Text("10") },
                isError = interestRateError,
                suffix = { Text("%") },
                label = { Text("Interest Rate") })
            Button(onClick = { TODO() }, shape = MaterialTheme.shapes.medium) {
                Text("Calculate")
            }

            Text("Payoff Date:")
            Text(
                payoffDate,
                color = MaterialTheme.colorScheme.primary,
            )
            Text("Total Interest Paid:")
            Text(
                totalInterestPaid,
                color = MaterialTheme.colorScheme.primary,
            )

        }
    }
}