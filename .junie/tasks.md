# Debt Annihilator Calculator—Task List

This project is broken down into four main phases: Setup, UI Development, Calculation Logic, and Finalization.

---

## Phase 1: Project Setup

- [ ] Initialize a new project in your chosen framework (e.g., create a new Android Studio project for a Kotlin app)
- [ ] Set up the basic file structure (e.g., main activity, layout file)

---

## Phase 2: User Interface (UI)

### Input Fields
- [ ] Add a text input for "Loan Total" (accepts numbers/currency)
- [ ] Add a text input for "Monthly Payment" (accepts numbers/currency)
- [ ] Add a text input for "Interest Rate" (accepts numbers/percentage)

### Action Button
- [ ] Add a "Calculate" button

### Output Display
- [ ] Add a text view to display the "Payoff Date"
- [ ] Add a text view to display the "Total Interest Paid"

---

## Phase 3: Calculation Logic

- [ ] Create a function that takes loanTotal, monthlyPayment, and interestRate as arguments
- [ ] Inside the function, calculate the monthly interest rate from the annual rate
- [ ] Implement a loop that subtracts the monthly payment from the loan balance and adds the calculated interest each month
- [ ] Keep a running total of interest paid
- [ ] Count the number of months until the loan balance is zero or less
- [ ] Convert the total months into a future date to find the "Payoff Date"
- [ ] Return the calculated payoffDate and totalInterestPaid

---

## Phase 4: Integration & Finalization

- [ ] Connect the "Calculate" button to the calculation function
- [ ] Pass the values from the input fields to the function
- [ ] Take the results from the function and display them in the corresponding output text views
- [ ] Perform basic input validation (e.g., ensure inputs are not empty and are valid numbers)
- [ ] Test the app with different values to ensure accuracy
- [ ] Project Complete
