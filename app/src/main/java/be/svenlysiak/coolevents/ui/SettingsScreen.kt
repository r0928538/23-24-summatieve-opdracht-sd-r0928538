package be.svenlysiak.coolevents.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.svenlysiak.coolevents.R


@Composable
fun SettingsScreen(modifier: Modifier = Modifier,
                   settingsViewModel: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory),
                   onClickDelete: () -> Unit,
                   onClickCity: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
        .padding(50.dp)){
        Text(text = stringResource(id = R.string.setCity))
        CityTextField(city = settingsViewModel.city, onValueChange = {settingsViewModel.updateCity(it)})
        Spacer(Modifier.height(10.dp))
        ChangeCityButton(settingsViewModel = settingsViewModel, onClick = onClickCity)
        Spacer(Modifier.height(50.dp))
        ChangePasswordTextField(password = settingsViewModel.password, settingsViewModel = settingsViewModel)
        Spacer(Modifier.height(10.dp))
        ChangePasswordButton(onClick = {settingsViewModel.updateUserPassword(); showDialog = true})
        Spacer(Modifier.height(100.dp))
        DeleteUserButton(settingsViewModel = settingsViewModel, onClick = onClickDelete)
    }

    if (showDialog) {
        PasswordChangedAlertDialog({showDialog = false})
    }

}

@Composable
fun CityTextField(city: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(value = city, onValueChange = onValueChange, label = { Text(stringResource(R.string.city)) },
        modifier = Modifier
            .fillMaxWidth())
}

@Composable
fun ChangeCityButton(modifier: Modifier = Modifier,
                     settingsViewModel: SettingsViewModel,
                     onClick: () -> Unit) {
    Button(onClick = { settingsViewModel.updateUserCity(); onClick() },
        modifier = Modifier
            .fillMaxWidth()) {
        Text(stringResource(R.string.changeCity))
    }
}

@Composable
fun ChangePasswordTextField(password: String, modifier: Modifier = Modifier,
                            settingsViewModel: SettingsViewModel) {
    TextField(value = password, onValueChange = {settingsViewModel.updatePassword(it)}, label = { Text(stringResource(R.string.password)) },
        modifier = Modifier
            .fillMaxWidth())
}

@Composable
fun ChangePasswordButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()) {
        Text(stringResource(R.string.changePassword))
    }
}

@Composable
fun DeleteUserButton(modifier: Modifier = Modifier,
                     settingsViewModel: SettingsViewModel,
                     onClick: () -> Unit){
    Button(onClick = {settingsViewModel.deleteUser(); onClick()},
        modifier = Modifier
            .fillMaxWidth()) {
        Text(stringResource(R.string.deleteUser))
    }
}