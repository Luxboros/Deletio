package com.luxboros.deletio.domain

import com.luxboros.deletio.helpers.toCurrencyString
import kotlinx.datetime.*
import kotlin.time.Clock

data class CalculationResult(
    val payoffDate: LocalDate?, val totalInterestPaid: Double?,
)

enum class ResultCardType {
    POSITIVE, INFO, WARNING
}

fun formatToCurrency(value: Double?, currencySymbol: String = "$"): String {
    return when (value) {
        null -> "${currencySymbol}0.00"
        -1.0 -> "Interest exceeds payment\nLoan balance will continue to grow."
        -2.0 -> "Can't calculate emptiness. Fill in all fields"
        else -> {
            "$currencySymbol${value.toCurrencyString()}"
        }
    }
}

@OptIn(kotlin.time.ExperimentalTime::class)
fun dateToString(date: LocalDate?): String {
    return when {
        date == null -> "Invalid Input"
        date < Clock.System.todayIn(TimeZone.currentSystemDefault()) -> "Definitely\nnot during your\nlifetime."
        else -> "${
            date.month.name.lowercase().replaceFirstChar { it.uppercase() }
        } ${date.year}"
    }
}

@OptIn(kotlin.time.ExperimentalTime::class)
fun calculate(
    monthlyPayment: String, interestRate: String, loanTotal: String,
): CalculationResult {
    if (monthlyPayment.isEmpty() || interestRate.isEmpty() || loanTotal.isEmpty()) return CalculationResult(
        null,
        -2.0
    )
    val monthlyRate = interestRate.toDouble() / 100.0 / 12.0
    val monthlyPaymentValue = monthlyPayment.toDouble()
    val loanTotalValue = loanTotal.toDouble()
    var leftoverLoanValue = loanTotalValue
    var totalInterestPaidValue = 0.0

    if (loanTotalValue > 0.0 && monthlyPaymentValue <= loanTotalValue * monthlyRate) {
        return CalculationResult(
            Clock.System.todayIn(TimeZone.currentSystemDefault())
                .minus(
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