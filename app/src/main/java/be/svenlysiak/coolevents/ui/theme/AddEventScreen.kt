package be.svenlysiak.coolevents.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.svenlysiak.coolevents.R
import be.svenlysiak.coolevents.models.Event
import be.svenlysiak.coolevents.models.User
import be.svenlysiak.coolevents.utils.DetailText
import be.svenlysiak.coolevents.utils.MockupUser
import java.util.Date

@Composable
fun AddEventScreen(modifier: Modifier = Modifier) {
    var organisator by remember { mutableStateOf("")}
    var titel by remember { mutableStateOf("")}
    var plaats by remember {mutableStateOf ("")}
    var eventType by remember {mutableStateOf ("")}
    var status by remember {mutableStateOf ("")}
    var wegmoeilijkheden by remember {mutableStateOf (false)}
    //ToDo -> not statefull -> when recomposition happens, a new ToDO object is created with the entered fields
    var event =
        Event(organisator, titel, Date(), plaats, eventType, status, wegmoeilijkheden)

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
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)){
            DropdownListUser()
        }
        SingleLineEditField(titel, {titel = it}, label = stringResource(id = R.string.titel))
        SingleLineEditField(plaats, {plaats = it}, label = stringResource(id = R.string.plaats))
        SingleLineEditField(eventType, {eventType = it}, label = stringResource(id = R.string.eventType))
        SingleLineEditField(status, {status = it}, label = stringResource(id = R.string.status))
        EditSwitch(text = stringResource(R.string.wegmoeilijkheden), checked = wegmoeilijkheden, onValueChange = {wegmoeilijkheden = it})
        SaveCancelButton(event = event)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownListUser() {
    val users = MockupUser.getUsers()
    var expanded by remember {
        mutableStateOf(false)
    }
    var user by remember {
        mutableStateOf<User?>(null)
    }
    Column {
        TextField(
            value = user?.userName ?: "",
            label = {Text(stringResource(R.string.organisator))},
            onValueChange = {
                expanded = true
            },
            modifier = Modifier
                .onFocusChanged {
                    expanded = it.hasFocus
                }
                .padding(top = 5.dp)
                .fillMaxWidth()
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            for (u in users) {
                DropdownMenuItem(text = { Text(u.userName) }, onClick = {
                    user = u
                    expanded = false
                })
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleLineEditField(value: String, onValueChange: (String) -> Unit, label : String,  modifier: Modifier = Modifier) {
    TextField(value = value, onValueChange = onValueChange, label = { Text(label)}, singleLine = true, modifier = modifier
        .fillMaxWidth()
        .padding(top = 5.dp))
}
@Composable
fun EditSwitch(text: String, checked: Boolean, onValueChange: (Boolean) -> Unit,  modifier: Modifier = Modifier) {
    Row{
        Column (
            modifier
                .weight(2f)
                .align(alignment = Alignment.CenterVertically)) {
            Text(text = text,
                modifier
                    .align(alignment = Alignment.End)
                    .padding(10.dp))
        }
        Column (modifier.weight(1f)){
            Switch(checked = checked, onCheckedChange = onValueChange)
        }
    }
}

@Composable
fun SaveCancelButton(event: Event, modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 50.dp)) {
        Button(onClick = {
            //nothing
        }, modifier = Modifier
            .weight(1f)
            .padding(10.dp) ) {
            Text(stringResource(R.string.cancel))
        }
        Button(onClick = {
            //save
        }, modifier = Modifier
            .weight(1f)
            .padding(10.dp)) {
            Text(stringResource(R.string.save))
        }
    }

}