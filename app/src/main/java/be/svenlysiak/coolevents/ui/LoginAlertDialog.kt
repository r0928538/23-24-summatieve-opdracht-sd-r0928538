package be.svenlysiak.coolevents.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.svenlysiak.coolevents.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginAlertDialog(modifier: Modifier = Modifier) {
    AlertDialog(onDismissRequest = {}) {
        Text(text = stringResource(R.string.registrationsuccess))
        OutlinedButton(
            onClick = {},
            modifier = Modifier.padding(10.dp)
        ) {
            Text(stringResource(R.string.ok))
        }
    }
}