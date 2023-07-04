package com.example.tiptime

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipTimeLayout()
                }
            }
        }
    }
}

@Composable
fun TipTimeLayout() {
    var roundUp by remember {
        mutableStateOf(false)
    }

    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    var rateinput by remember { mutableStateOf("") }
    val rate = rateinput.toDoubleOrNull() ?: 0.0
    var peoplenumber by remember { mutableStateOf("") }
    val people = peoplenumber.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, rate, people, roundUp)
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(
            value = amountInput,
            onValueChange = { amountInput = it },
            leadingIcon = R.drawable.money,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        Percentage(
            rate = rateinput, onValueChange = { rateinput = it },
            percentIcon = R.drawable.percent,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        numberPeople(
            people = peoplenumber, onValueChange = { peoplenumber = it }, modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        roundUp(
            roundUp = roundUp,
            roundUpChanged = { roundUp = it },
            modifier = Modifier.padding(bottom = 32.dp)
        )
        //  Spacer(modifier = Modifier.height(150.dp))
    }
}

@Composable
fun Percentage(
    @DrawableRes percentIcon: Int,
    rate: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    TextField(
        value = rate,
//        percentIcon = {
//         //   Icon(painter = painterResource(id = R.drawable.percent), contentDescription = )
//        },
        onValueChange = { newValue: String ->
            onValueChange(newValue)
        },
        singleLine = true,
        label = { Text(stringResource(R.string.percentage)) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        modifier = modifier
    )
}

@Composable
fun EditNumberField(
    @DrawableRes leadingIcon: Int,

    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
        onValueChange = { newValue: String ->
            onValueChange(newValue)
        },
        singleLine = true,
        label = { Text(stringResource(R.string.bill_amount)) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        modifier = modifier
    )
}

@Composable
fun numberPeople(
    people: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = people,
        onValueChange = { newValue: String ->
            onValueChange(newValue)
        },
        singleLine = true,
        label = { Text(stringResource(R.string.people_number)) },

        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),

        modifier = modifier
    )
}

@Composable
fun roundUp(
    modifier: Modifier = Modifier,
    roundUp: Boolean,
    roundUpChanged: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .height(16.dp),
        //  .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.round_up_tip))
        Switch(
            checked = roundUp, onCheckedChange = roundUpChanged,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(Alignment.End)
        )


    }
}

private fun calculateTip(
    amount: Double,
    tipPercentage: Double,
    nupeople: Double,
    roundUp: Boolean
): String {
    var tip = (tipPercentage / 100 * amount) / nupeople
    if (roundUp)
        tip = kotlin.math.ceil(tip)
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {
        TipTimeLayout()
    }
}

