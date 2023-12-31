package be.svenlysiak.coolevents.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import be.svenlysiak.coolevents.R
import be.svenlysiak.coolevents.utils.DetailText

@Composable
fun AddEventScreen(modifier: Modifier = Modifier,
                   addEventViewModel: AddEventViewModel = viewModel(factory = AppViewModelProvider.Factory),
                   onCancelClicked: () -> Unit,
                   onSaveClicked: () -> Unit
) {
    var eventType by remember { mutableStateOf("")}
    var description by remember {mutableStateOf ("")}
    var cities by remember {mutableStateOf ("")}

    var startDay by remember {mutableStateOf ("")}
    var startMonth by remember {mutableStateOf ("")}
    var startYear by remember {mutableStateOf ("")}

    var endDay by remember {mutableStateOf ("")}
    var endMonth by remember {mutableStateOf ("")}
    var endYear by remember {mutableStateOf ("")}

    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(10.dp)) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            DetailText(text = (stringResource(R.string.voegEventToe)))
        }
        SingleLineEditField(eventType, {eventType = it}, label = stringResource(id = R.string.eventType))
        SingleLineEditField(cities, {cities = it}, label = stringResource(id = R.string.plaats))
        DescriptonTextField(description = description, {description = it})

        Text(stringResource(id = R.string.startdate))
        Row(){
            NumberEditField(value = startDay, onValueChange =
            {
                if (it != "") {
                    if (it.isDigitsOnly()) {
                        if ((it.length <= 2).and(it.toInt() <= 31).and(it.toInt() > 0)) {
                            startDay = it
                        }
                    }
                }
            },
                label = stringResource(
                id = R.string.day
            ))
            NumberEditField(value = startMonth, onValueChange =
            {
                if (it != "") {
                    if (it.isDigitsOnly()) {
                        if ((it.length <= 2).and(it.toInt() <= 12).and(it.toInt() > 0)) {
                            startMonth = it
                        }
                    }
                }
            },
                label = stringResource(
                id = R.string.month
            ))
            NumberEditField(value = startYear, onValueChange =
            {if (it != "") {
                if (it.isDigitsOnly()) {
                    if ((it.length <= 4).and(it.toInt() > 0)) {
                        startYear = it
                    }
                }
            }
            },
                label = stringResource(
                id = R.string.year
            ))
        }
        Divider()

        Text(stringResource(id = R.string.enddate))
        Row(){
            NumberEditField(value = endDay, onValueChange =
            {if (it != "") {
                if (it.isDigitsOnly()) {
                    if ((it.length <= 2).and(it.toInt() <= 31).and(it.toInt() > 0)) {
                        endDay = it
                    }
                }
            }
            },
                label = stringResource(
                    id = R.string.day
                ))
            NumberEditField(value = endMonth, onValueChange =
            {if (it != "") {
                if (it.isDigitsOnly()) {
                    if ((it.length <= 2).and(it.toInt() <= 12).and(it.toInt() > 0)) {
                        endMonth = it
                    }
                }
            }
            },
                label = stringResource(
                    id = R.string.month
                ))
            NumberEditField(value = endYear, onValueChange =
            {if (it != "") {
                if (it.isDigitsOnly()) {
                    if ((it.length <= 4).and(it.toInt() > 0)) {
                        endYear = it
                    }
                }
            }
            },
                label = stringResource(
                    id = R.string.year
                ))
        }
        Divider()

        Row(){
            SaveButton(onSaveClick = {
                addEventViewModel.saveEvent(
                    eventType, description,
                    startDay, startMonth, startYear,
                    endDay, endMonth, endYear,
                    cities
                )
                onSaveClicked()
            }
            )
            CancelButton(onCancelClick = onCancelClicked)
        }
    }
}

@Composable
fun NumberEditField(value: String, onValueChange: (String) -> Unit, label : String,  modifier: Modifier = Modifier) {
    TextField(value = value, onValueChange = onValueChange,
        label = { Text(label)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true, modifier = modifier
            .padding(top = 5.dp)
            .width(125.dp))
}

@Composable
fun SingleLineEditField(value: String, onValueChange: (String) -> Unit, label : String,  modifier: Modifier = Modifier) {
    TextField(value = value, onValueChange = onValueChange, label = { Text(label)}, singleLine = true, modifier = modifier
        .fillMaxWidth()
        .padding(top = 5.dp))
}

@Composable
fun DescriptonTextField(description: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = description,
        onValueChange = onValueChange,
        minLines = 5,
        maxLines = 5,
        label = {Text(stringResource(R.string.description))},
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp))
}

@Composable
fun SaveButton(onSaveClick: () -> Unit, modifier: Modifier = Modifier) {
        Button(onClick = onSaveClick,
            modifier = Modifier
            .padding(10.dp)) {
            Text(stringResource(R.string.save))
        }
}

@Composable
fun CancelButton(onCancelClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onCancelClick, modifier = Modifier
        .padding(10.dp) ) {
        Text(stringResource(R.string.cancel))
    }
}