package be.svenlysiak.coolevents.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.svenlysiak.coolevents.R

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory), loginSuccess: () -> Unit,
                modifier: Modifier = Modifier) {

    var showRegistrationSuccessDialog by remember { mutableStateOf(false) }
    var showRegistrationFailureDialog by remember { mutableStateOf(false) }

    Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .padding(40.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.loginTitle))
            Spacer(Modifier.height(10.dp))
            UsernameTextField(userName = loginViewModel.username , onValueChange = {loginViewModel.updateUserName(it)})
            Spacer(Modifier.height(10.dp))
            PasswordTextField(password = loginViewModel.password, onValueChange = {loginViewModel.updatePassword(it)})
            Spacer(Modifier.height(10.dp))
            Row {
                LoginButton {
                    loginViewModel.login(loginSuccess)

                }
                RegistrationButton() {
                    loginViewModel.register()
                    if(loginViewModel.validateInput()){
                        showRegistrationSuccessDialog = true
                    }
                    else{
                        showRegistrationFailureDialog = true
                    }
                }
                }
            }
        Spacer(Modifier.height(100.dp))

        LoginAsGuestButton {
            loginSuccess()

        }
    }
    if (showRegistrationSuccessDialog) {
        RegistrationSuccessAlertDialog({showRegistrationSuccessDialog = false})
    }
    if (showRegistrationFailureDialog) {
        RegistrationFailureAlertDialog({showRegistrationFailureDialog = false})
    }
    }




@Composable
fun UsernameTextField(userName: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(value = userName, onValueChange = onValueChange, label = { Text(stringResource(R.string.username)) })
}

@Composable
fun PasswordTextField(password: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(value = password, onValueChange = onValueChange, label = { Text(stringResource(R.string.password)) }, visualTransformation = PasswordVisualTransformation())
}

@Composable
fun LoginButton(onClick : () -> Unit) {
    Button(onClick = onClick) {
        Text(stringResource(R.string.login))
    }
}
@Composable
fun RegistrationButton(onClick : () -> Unit) {
    Button(onClick = onClick) {
        Text(stringResource(R.string.newUser))
    }
}

@Composable
fun LoginAsGuestButton(onClick : () -> Unit) {
    Button(onClick = onClick) {
        Text(stringResource(R.string.loginAsGuest))
    }
}

