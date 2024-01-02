package be.svenlysiak.coolevents.ui

import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.svenlysiak.coolevents.R
import be.svenlysiak.coolevents.data.EventApiState
import be.svenlysiak.coolevents.data.MyConfiguration
import be.svenlysiak.coolevents.data.userEvents.UserEvent
import be.svenlysiak.coolevents.models.Event
import be.svenlysiak.coolevents.utils.DetailText


@Composable
fun ListScreen(modifier: Modifier = Modifier,
               listEventViewModel: EventListViewModel = viewModel(factory = AppViewModelProvider.Factory),
               onclickEvent: (Event) -> Unit,
               onclickUserEvent: (UserEvent) -> Unit) {

    Column {
        TodayDateText()
        WhoLoggedInText()
        EventState(listEventViewModel = listEventViewModel, modifier, onclickEvent, onclickUserEvent)
    }
}

@Composable
fun WhoLoggedInText(modifier : Modifier = Modifier){
    Row( modifier = modifier
        .fillMaxWidth()
        .padding(start = 10.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
        ){
        if(MyConfiguration.loggedInUser!=null){
            Text(stringResource(R.string.welcome) + MyConfiguration.loggedInUser?.username.toString())
        }
        else{
            Text(stringResource(R.string.notLoggedIn))
        }
    }
}

@Composable
fun TodayDateText(modifier : Modifier = Modifier){
    Row {
        val cal: Calendar = Calendar.getInstance()
        var today = ""
        today += cal.get(Calendar.DAY_OF_MONTH)
        today += " "
        when (cal.get(Calendar.MONTH) + 1) {
            1 -> today += (stringResource(R.string.january))
            2 -> today += (stringResource(R.string.february))
            3 -> today += (stringResource(R.string.march))
            4 -> today += (stringResource(R.string.april))
            5 -> today += (stringResource(R.string.may))
            6 -> today += (stringResource(R.string.june))
            7 -> today += (stringResource(R.string.july))
            8 -> today += (stringResource(R.string.augustus))
            9 -> today += (stringResource(R.string.september))
            10 -> today += (stringResource(R.string.october))
            11 -> today += (stringResource(R.string.november))
            12 -> today += (stringResource(R.string.december))
        }
        today += " "
        today += cal.get(Calendar.YEAR)
        DetailText(text = today)
    }
}

@Composable
fun EventState(listEventViewModel: EventListViewModel, modifier : Modifier = Modifier,
               onclickEvent: (Event) -> Unit, onclickUserEvent: (UserEvent) -> Unit) {
    val uiState by listEventViewModel.uistate.collectAsState()
    when (uiState.apiState) {
        is EventApiState.Loading -> Text(stringResource(id = R.string.loading))
        is EventApiState.Success -> ListEvents(events =
        (uiState.apiState as EventApiState.Success).eventsSuccess,
            onclickEvent, userEvents = uiState.userEvents, onclickUserEvent)
        is EventApiState.Error -> Text(stringResource(id = R.string.dataError))
    }

}

@Composable
fun ListEvents(events: List<Event>, onclickEvent: (Event) -> Unit, userEvents: List<UserEvent>,
               onclickUserEvent: (UserEvent) -> Unit) {
    LazyColumn {
        if(userEvents.isNotEmpty()){
            item { Text(stringResource(id = R.string.yourEvents)) }
            items(userEvents){ userEvent ->
                UserEventCard(userEvent, modifier = Modifier
                    .padding(5.dp)
                    .clickable {
                        onclickUserEvent(userEvent)
                    })
            }
            item { Divider() }
            item { Text(stringResource(id = R.string.officialEvents)) }
        }
        items(events) { event ->
            EventCard(event, modifier = Modifier
                .padding(5.dp)
                .clickable {
                    onclickEvent(event)
                })
        }
    }
}

@Composable
fun EventCard(event: Event, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .height(180.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp)) {
            Text("${event.startDateTime}", modifier = Modifier
                .padding(5.dp)
                .weight(4f))
            Column(
                Modifier
                    .weight(10f)) {
                Text(text = event.cities, modifier = modifier.fillMaxWidth() )
                Divider()
                Text(stringResource(R.string.descriptionInCard) + event.description)
            }
        }

    }
}

@Composable
fun UserEventCard(userEvent: UserEvent, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .height(180.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp)) {
            Text(userEvent.startDateTime, modifier = Modifier
                .padding(5.dp)
                .weight(4f))
            Column(
                Modifier
                    .weight(10f)) {
                Text(text = userEvent.cities, modifier = modifier.fillMaxWidth() )
                Divider()
                Text(stringResource(R.string.descriptionInCard) + userEvent.description)
            }
        }

    }
}