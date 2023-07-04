//package com.example.tiptime
//
//@Composable
//fun TipTimeLayout() {
//    var amountInput by remember { mutableStateOf("") }
//
//    val amount = amountInput.toDoubleOrNull() ?: 0.0
//    val tip = calculateTip(amount)
//
//    Column(
//        modifier = Modifier.padding(40.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = stringResource(R.string.calculate_tip),
//            modifier = Modifier
//                .padding(bottom = 16.dp)
//                .align(alignment = Alignment.Start)
//        )
//        EditNumberField(
//            value = amountInput,
//            onValueChanged = { amountInput = it },
//            modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth()
//        )
//        Text(
//            text = stringResource(R.string.tip_amount, tip),
//            style = MaterialTheme.typography.displaySmall
//        )
//        Spacer(modifier = Modifier.height(150.dp))
//    }
//}
//
//@Composable
//fun EditNumberField(
//    value: String,
//    onValueChanged: (String) -> Unit,
//    modifier: Modifier
//) {
//    TextField(
//        value = value,
//        singleLine = true,
//        modifier = modifier,
//        onValueChange = onValueChanged,
//        label = { Text(stringResource(R.string.bill_amount)) },
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//    )
//}
//
///**
// * Calculates the tip based on the user input and format the tip amount
// * according to the local currency.
// * Example would be "$10.00".
// */
//private fun calculateTip(amount: Double, tipPercent: Double = 15.0): String {
//    val tip = tipPercent / 100 * amount
//    return NumberFormat.getCurrencyInstance().format(tip)
//}
//
//@Preview(showBackground = true)
//@Composable
//fun TipTimeLayoutPreview() {
//    TipTimeTheme {
//        TipTimeLayout()
//    }
//}
