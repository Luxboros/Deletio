package com.luxboros.deletio.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.luxboros.deletio.components.InputCard
import com.luxboros.deletio.domain.calculate
import com.luxboros.deletio.domain.dateToString
import com.luxboros.deletio.domain.formatToCurrency
import com.luxboros.deletio.ui.theme.CustomTheme
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.ui.tooling.preview.Preview

var regexPattern = Regex("^\\d+(\\.\\d{0,2})?$")

@Composable
@Preview
fun DeletioScreen() {
    CustomTheme {
        var loanTotal by remember { mutableStateOf("400000") } // TODO Reset to empty before deploying
        var monthlyPayment by remember { mutableStateOf("2383") }// TODO Reset to empty before deploying
        var interestRate by remember { mutableStateOf("5.95") }// TODO Reset to empty before deploying
        var payoffDate by remember { mutableStateOf<LocalDate?>(null) }
        var totalInterestPaid by remember { mutableStateOf<Double?>(null) }
        var loanTotalError by remember { mutableStateOf(false) }
        var monthlyPaymentError by remember { mutableStateOf(false) }
        var interestRateError by remember { mutableStateOf(false) }
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier.safeContentPadding().fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

                ) {

                InputCard(
                    loanTotal,
                    loanTotalError,
                    onLoanTotalChange = { it ->
                        loanTotal = it
                        loanTotalError =
                            it.isEmpty() || !it.matches(regexPattern)
                    },
                    monthlyPayment,
                    monthlyPaymentError,

                    onMonthlyPaymentChange = { it ->
                        monthlyPayment = it
                        monthlyPaymentError =
                            it.isEmpty() || !it.matches(regexPattern)
                    },
                    interestRate,
                    interestRateError,
                    onInterestRateChange = { it ->
                        interestRate = it
                        interestRateError =
                            it.isEmpty() || !it.matches(regexPattern)
                    },
                )
                Spacer(modifier = Modifier.weight(1f))

                Text("Payoff Date:")
                Text(
                    dateToString(payoffDate),


                    )
                Text("Total Interest Paid:")
                Text(
                    formatToCurrency(totalInterestPaid),
                )
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

            }
        }
    }
}