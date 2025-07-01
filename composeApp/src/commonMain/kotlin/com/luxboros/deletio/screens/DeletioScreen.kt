package com.luxboros.deletio.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luxboros.deletio.components.CalculateButton
import com.luxboros.deletio.components.InputCard
import com.luxboros.deletio.components.OutputSection
import com.luxboros.deletio.domain.ResultCardType
import com.luxboros.deletio.domain.calculate
import com.luxboros.deletio.domain.dateToString
import com.luxboros.deletio.domain.formatToCurrency
import com.luxboros.deletio.ui.theme.CustomTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.ui.tooling.preview.Preview

var regexPattern = Regex("^\\d+(\\.\\d{0,2})?$")


@Composable
@Preview
fun DeletioScreen() {
    CustomTheme {
        var loanTotal by remember { mutableStateOf<String>("") }
        var monthlyPayment by remember { mutableStateOf<String>("") }
        var interestRate by remember { mutableStateOf<String>("") }
        var payoffDate by remember { mutableStateOf<LocalDate?>(null) }
        var totalInterestPaid by remember { mutableStateOf<Double?>(null) }
        var loanTotalError by remember { mutableStateOf(false) }
        var monthlyPaymentError by remember { mutableStateOf(false) }
        var interestRateError by remember { mutableStateOf(false) }
        var showResults by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier.fillMaxSize().safeDrawingPadding()
                    .padding(16.dp),
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

                OutputSection(
                    dateToString(payoffDate),
                    formatToCurrency(totalInterestPaid),
                    showResults,
                    resultType = (if (totalInterestPaid != null && totalInterestPaid!! < 0.0) ResultCardType.WARNING else ResultCardType.POSITIVE)
                )
                Spacer(modifier = Modifier.height(16.dp))
                CalculateButton(
                    onClick = {
                        scope.launch {

                            showResults = false
                            delay(300)
                            val result = calculate(
                                monthlyPayment, interestRate, loanTotal
                            )
                            payoffDate = result.payoffDate
                            totalInterestPaid = result.totalInterestPaid
                            showResults = true
                        }
                    },
                    isEnabled = !(loanTotalError || monthlyPaymentError || interestRateError)
                )
                Spacer(modifier = Modifier.height(8.dp))


            }
        }
    }
}

