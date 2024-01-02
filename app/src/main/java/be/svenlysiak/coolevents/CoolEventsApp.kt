package be.svenlysiak.coolevents

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import be.svenlysiak.coolevents.data.MyConfiguration
import be.svenlysiak.coolevents.ui.AddEventScreen
import be.svenlysiak.coolevents.ui.ListScreen
import be.svenlysiak.coolevents.ui.LoginScreen
import be.svenlysiak.coolevents.ui.SettingsScreen
import be.svenlysiak.coolevents.ui.DetailScreen

enum class EventScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Calendar(title = R.string.calendar),
    AddEvent(title = R.string.addEvent),
    Settings(title = R.string.settings),
    Detail(title = R.string.detail)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventAppBar(
    currentScreen: EventScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    showAddAction: Boolean,
    showSettings: Boolean,
    navigateToAddEvent: () -> Unit,
    navigateToSettings: () -> Unit,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }, actions = {
            if (showAddAction) {
                IconButton(onClick =
                navigateToAddEvent
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add event"
                    )
                }
            }
            if (showSettings) {
                IconButton(onClick =
                navigateToSettings
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Settings"
                    )
                }
            }
        }
    )
}


@Composable
fun CoolEventsApp(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = EventScreen.valueOf(
        backStackEntry?.destination?.route ?: EventScreen.Start.name
    )
    var showAddAction by remember {
        mutableStateOf(false)
    }
    var showSettings by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            EventAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    if (navController.previousBackStackEntry?.destination?.route.equals(EventScreen.Start.name)) {
                        MyConfiguration.loggedInUser = null
                    }
                    navController.navigateUp()
                },
                showAddAction = showAddAction,
                navigateToAddEvent = {
                    navController.navigate(EventScreen.AddEvent.name)
                },
                showSettings = showSettings,
                navigateToSettings = {
                    navController.navigate(EventScreen.Settings.name)
                }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = EventScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = EventScreen.Start.name) {
                showAddAction = false
                showSettings = false
                LoginScreen(
                    loginSuccess = {
                        navController.navigate(EventScreen.Calendar.name)
                    }
                )
            }
            composable(route = EventScreen.Detail.name) {
                showAddAction = false
                showSettings = false
                DetailScreen(onClick = { resetList(navController)})
            }
            composable(route = EventScreen.Calendar.name) {
                //Only logged in users can add events
                if(MyConfiguration.loggedInUser != null){
                    showAddAction = true
                    showSettings = true
                }
                ListScreen(onclickEvent = {
                    MyConfiguration.selectedEvent = (it)
                    navController.navigate(EventScreen.Detail.name)
                },
                    onclickUserEvent = {MyConfiguration.selectedEvent = (it)
                        navController.navigate(EventScreen.Detail.name)})
            }
            composable(route = EventScreen.Settings.name) {
                showAddAction = false
                showSettings = false
                SettingsScreen(
                    onClickDelete = {navController.popBackStack(EventScreen.Start.name, true)
                    navController.navigate(EventScreen.Start.name)},
                    onClickCity = { resetList(navController) }
                )
            }
            composable(route = EventScreen.AddEvent.name) {
                showAddAction = false
                showSettings = false
                AddEventScreen(onCancelClicked = {navigateUp(navController)},
                    onSaveClicked = { resetList(navController) }) }
                }

            }
        }


fun navigateUp(navController: NavHostController) {
    if (navController.previousBackStackEntry?.destination?.route.equals(EventScreen.Start.name)) {
        //reset loggedinUser
        MyConfiguration.loggedInUser = null
    }
    MyConfiguration.selectedEvent = null
    navController.navigateUp()
}

fun resetList(navController: NavHostController) {
    navController.navigate(EventScreen.Calendar.name)
    navController.popBackStack(EventScreen.Calendar.name, false)
}
