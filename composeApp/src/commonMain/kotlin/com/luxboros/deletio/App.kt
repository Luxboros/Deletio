package com.luxboros.deletio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.luxboros.deletio.helpers.toCurrencyString
import com.luxboros.deletio.ui.theme.DeletioTheme
import kotlinx.datetime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

data class CalculationResult(
    val payoffDate: LocalDate?, val totalInterestPaid: Double?,
)

fun formatToCurrency(value: Double?, currencySymbol: String = "$"): String {
    return when (value) {
        null -> "${currencySymbol}0.00"
        -1.0 -> "Payment is too low, total value accruing."
        else -> {
            "$currencySymbol${value.toCurrencyString()}"
        }
    }
}

fun dateToString(date: LocalDate?): String {
    return when {
        date == null -> "N/A"
        date < Clock.System.todayIn(TimeZone.currentSystemDefault()) -> "Definitely not during your lifetime"
        else -> "${
            date.month.name.lowercase().replaceFirstChar { it.uppercase() }
        } ${date.year}"
    }
}

fun calculate(
    monthlyPayment: String, interestRate: String, loanTotal: String,
): CalculationResult {
    val monthlyRate = interestRate.toDouble() / 100.0 / 12.0
    val monthlyPaymentValue = monthlyPayment.toDouble()
    val loanTotalValue = loanTotal.toDouble()
    var leftoverLoanValue = loanTotalValue
    var totalInterestPaidValue = 0.0

    if (loanTotalValue > 0.0 && monthlyPaymentValue <= loanTotalValue * monthlyRate) {
        return CalculationResult(
            Clock.System.todayIn(TimeZone.currentSystemDefault()).minus(
                1, DateTimeUnit.MONTH
            ), -1.0
        )
    }

    var monthsToPayOff = 0
    while (leftoverLoanValue > 0) {
        val interest = leftoverLoanValue * monthlyRate
        totalInterestPaidValue += interest
        leftoverLoanValue += interest - monthlyPaymentValue
        monthsToPayOff++
    }
    val payoffDateValue = Clock.System.todayIn(TimeZone.currentSystemDefault())
        .plus(monthsToPayOff, DateTimeUnit.MONTH)

    return CalculationResult(
        payoffDateValue, totalInterestPaidValue
    )

}

@Composable
@Preview
fun App() {
    DeletioTheme {
        var loanTotal by remember { mutableStateOf("400000") } // TODO Reset to empty before deploying
        var monthlyPayment by remember { mutableStateOf("2383") }// TODO Reset to empty before deploying
        var interestRate by remember { mutableStateOf("5.95") }// TODO Reset to empty before deploying
        var payoffDate by remember { mutableStateOf<LocalDate?>(null) }
        var totalInterestPaid by remember { mutableStateOf<Double?>(null) }
        var loanTotalError by remember { mutableStateOf(false) }
        var monthlyPaymentError by remember { mutableStateOf(false) }
        var interestRateError by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier.safeContentPadding().fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            OutlinedTextField(
                value = loanTotal,
                onValueChange = { it ->
                    loanTotal = it
                    loanTotalError =
                        !it.contains(Regex("^\\d+(\\.\\d{0,2})?$")) || it.isEmpty()
                },
                singleLine = true,
                placeholder = { Text("500000") },
                isError = loanTotalError,
                label = {
                    Text("Loan Total")
                },
            )
            OutlinedTextField(
                singleLine = true,
                value = monthlyPayment,
                onValueChange = { it ->
                    monthlyPayment = it
                    monthlyPaymentError =
                        !it.contains(Regex("^\\d+(\\.\\d{0,2})?$")) || it.isEmpty()
                },
                placeholder = { Text("3330.32") },
                isError = monthlyPaymentError,
                label = { Text("Monthly Payment") })
            OutlinedTextField(
                singleLine = true,
                value = interestRate,
                onValueChange = { it ->
                    interestRate = it
                    interestRateError =
                        !it.contains(Regex("^\\d+(\\.\\d{0,2})?$")) || it.isEmpty()
                },
                placeholder = { Text("7.99") },
                isError = interestRateError,
                suffix = { Text("%") },
                label = { Text("Interest Rate") })
            Button(
                onClick = {
                    val result = calculate(
                        monthlyPayment, interestRate, loanTotal
                    )
                    payoffDate = result.payoffDate
                    totalInterestPaid = result.totalInterestPaid
                },
                enabled = !(loanTotalError || monthlyPaymentError || interestRateError)
            ) {
                Text("Calculate")
            }

            Text("Payoff Date:")
            Text(
                dateToString(payoffDate),


                )
            Text("Total Interest Paid:")
            Text(
                formatToCurrency(totalInterestPaid),
            )

        }
    }
}

