package be.svenlysiak.coolevents.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.svenlysiak.coolevents.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationSuccessAlertDialog(onDismiss: () -> Unit,
                                   modifier: Modifier = Modifier) {
    AlertDialog(onDismissRequest = {onDismiss()}) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 50.dp)
            .background(Color.LightGray)) {
            Text(text = stringResource(R.string.registrationSuccess))
            OutlinedButton(
                onClick = {onDismiss()},
                modifier = Modifier.padding(10.dp)
            ) {
                Text(stringResource(R.string.ok))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationFailureAlertDialog(onDismiss: () -> Unit,
                                   modifier: Modifier = Modifier) {
    AlertDialog(onDismissRequest = {onDismiss()}) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 50.dp)
                .background(Color.LightGray)) {
            Text(text = stringResource(R.string.registrationFailure))
            OutlinedButton(
                onClick = {onDismiss()},
                modifier = Modifier.padding(10.dp)
            ) {
                Text(stringResource(R.string.ok))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordChangedAlertDialog(onDismiss: () -> Unit,
                                   modifier: Modifier = Modifier) {
    AlertDialog(onDismissRequest = {onDismiss()}) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 50.dp)
                .background(Color.LightGray)) {
            Text(text = stringResource(R.string.passwordChanged))
            OutlinedButton(
                onClick = {onDismiss()},
                modifier = Modifier.padding(10.dp)
            ) {
                Text(stringResource(R.string.ok))
            }
        }
    }
}