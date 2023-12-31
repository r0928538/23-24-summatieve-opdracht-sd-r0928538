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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.svenlysiak.coolevents.R

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory), loginSuccess: () -> Unit,
                modifier: Modifier = Modifier) {
    Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxSize()) {
        var showdialog = false;
        Column(modifier = Modifier
            .padding(40.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.logintitle))
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
                }
                }
            }
        Spacer(Modifier.height(100.dp))

        LoginAsGuestButton {
            loginSuccess()
        }
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
        Text(stringResource(R.string.newuser))
    }
}

@Composable
fun LoginAsGuestButton(onClick : () -> Unit) {
    Button(onClick = onClick) {
        Text(stringResource(R.string.loginasguest))
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(loginSuccess = {})
}
