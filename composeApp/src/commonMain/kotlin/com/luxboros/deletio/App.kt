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
import androidx.compose.ui.unit.sp
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.DecimalMode
import com.ionspin.kotlin.bignum.decimal.RoundingMode
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlinx.datetime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

data class CalculationResult(
    val payoffDate: LocalDate?, val totalInterestPaid: BigDecimal?
)

fun formatToCurrency(value: BigDecimal?, currencySymbol: String = "$"): String {
    println(value)
    return when (value) {
        null -> "${currencySymbol}0.00"
        BigDecimal.fromInt(-1) -> "You're not even paying the interest."
        BigDecimal.fromInt(-2) -> "A bit more than the world's debt."
        else -> {
            val stringValue =
                value.roundToDigitPosition(4, RoundingMode.ROUND_HALF_CEILING)
                    .toPlainString()
            val parts = stringValue.split(".")
            val integers = parts[0]
            val decimals = parts.getOrNull(1)?.padEnd(2, '0') ?: "00"
            "$currencySymbol$integers.$decimals"
        }
    }
}

fun dateToString(date: LocalDate?): String {
    return when {
        date == null -> "N/A"
        date < Clock.System.todayIn(TimeZone.currentSystemDefault()) -> "Don't bother, you will need to Philip J. Fry it up"
        else -> "${
            date.month.name.lowercase().replaceFirstChar { it.uppercase() }
        } ${date.year}"
    }
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        var loanTotal by remember { mutableStateOf("1000") }
        var monthlyPayment by remember { mutableStateOf("100") }
        var interestRate by remember { mutableStateOf("0") }
        var payoffDate by remember { mutableStateOf<LocalDate?>(null) }
        var totalInterestPaid by remember { mutableStateOf<BigDecimal?>(null) }
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
                        it.contains(Regex("[^0-9.]")) || it.isEmpty()
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
                        it.contains(Regex("[^0-9.]")) || it.isEmpty()
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
                        it.contains(Regex("[^0-9.]")) || it.isEmpty()
                },
                placeholder = { Text("10") },
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
                shape = MaterialTheme.shapes.medium,
                enabled = !(loanTotalError || monthlyPaymentError || interestRateError)
            ) {
                Text("Calculate")
            }

            Text("Payoff Date:")
            Text(
                dateToString(payoffDate),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 40.sp


            )
            Text("Total Interest Paid:")
            Text(
                formatToCurrency(totalInterestPaid),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 40.sp
            )

        }
    }
}

fun calculate(
    monthlyPayment: String, interestRate: String, loanTotal: String
): CalculationResult {
    val calculationDecimalMode =
        DecimalMode(7, RoundingMode.ROUND_HALF_CEILING, 7)
    val monthlyRate = interestRate.toBigDecimal()
        .divide(BigDecimal.fromInt(100), calculationDecimalMode).divide(
            BigDecimal.fromInt(12), calculationDecimalMode
        )
    val monthlyPaymentValue = monthlyPayment.toBigDecimal()
    val loanTotalValue = loanTotal.toBigDecimal()
    var leftoverLoanValue = loanTotalValue
    var totalInterestPaidValue = BigDecimal.ZERO

    if (loanTotalValue > BigDecimal.ZERO && monthlyPaymentValue <= loanTotalValue.multiply(
            monthlyRate
        )
    ) {
        return CalculationResult(
            Clock.System.todayIn(TimeZone.currentSystemDefault()).minus(
                1, DateTimeUnit.MONTH
            ), BigDecimal.fromInt(-1)
        )
    }

    var monthsToPayOff = 0
    while (leftoverLoanValue > 0) {
        leftoverLoanValue -= monthlyPaymentValue
        val interest = leftoverLoanValue * monthlyRate
        totalInterestPaidValue += interest
        leftoverLoanValue += interest
        monthsToPayOff++
    }
    try {

        println("Months: $monthsToPayOff")
        val payoffDateValue =
            Clock.System.todayIn(TimeZone.currentSystemDefault())
                .plus(monthsToPayOff, DateTimeUnit.MONTH)

        return CalculationResult(
            payoffDateValue, totalInterestPaidValue
        )
    } catch (e: ArithmeticException) {
        println("Caught exception trying to convert to Int: ${e.message}")

        return CalculationResult(
            Clock.System.todayIn(TimeZone.currentSystemDefault()).minus(
                1, DateTimeUnit.MONTH
            ), (-2).toBigDecimal()
        )
    }
}