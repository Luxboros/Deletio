package com.luxboros.deletio.domain

import com.luxboros.deletio.helpers.toCurrencyString
import kotlinx.datetime.*

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