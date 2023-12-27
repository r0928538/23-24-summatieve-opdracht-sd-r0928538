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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.svenlysiak.coolevents.R
import be.svenlysiak.coolevents.data.EventApiState
import be.svenlysiak.coolevents.data.MyConfiguration
import be.svenlysiak.coolevents.models.Event
import be.svenlysiak.coolevents.utils.DetailText


@Composable
fun CalendarScreen(listEventViewModel: EventListViewModel,
                   modifier: Modifier = Modifier, onclick: (Event) -> Unit) {
    Column {
        TodayDateText()
        WhoLoggedInText()
        EventState(listEventViewModel = listEventViewModel, modifier, onclick)

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
            Text(stringResource(R.string.notloggedin))
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
            1 -> today += (stringResource(R.string.januari))
            2 -> today += (stringResource(R.string.februari))
            3 -> today += (stringResource(R.string.maart))
            4 -> today += (stringResource(R.string.april))
            5 -> today += (stringResource(R.string.mei))
            6 -> today += (stringResource(R.string.juni))
            7 -> today += (stringResource(R.string.juli))
            8 -> today += (stringResource(R.string.augustus))
            9 -> today += (stringResource(R.string.september))
            10 -> today += (stringResource(R.string.oktober))
            11 -> today += (stringResource(R.string.november))
            12 -> today += (stringResource(R.string.december))
        }
        today += " "
        today += cal.get(Calendar.YEAR)
        DetailText(text = today)
    }
}

@Composable
fun EventState(listEventViewModel: EventListViewModel, modifier : Modifier = Modifier, onclick: (Event) -> Unit) {
    val uiState by listEventViewModel.uistate.collectAsState()
    when (uiState.apiState) {
        is EventApiState.Loading -> Text(stringResource(id = R.string.loading))
        is EventApiState.Success -> ListEvents(events = (uiState.apiState as EventApiState.Success).eventsSuccess, onclick)
        is EventApiState.Error -> Text(stringResource(id = R.string.dataerror))
    }

}

@Composable
fun ListEvents(events: List<Event>, onclick: (Event) -> Unit) {
    LazyColumn {
        items(events) { event ->
            EventCard(event, modifier = Modifier
                .padding(5.dp)
                .clickable {
                    onclick(event)
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
                Text(stringResource(R.string.description) + event.description)
            }
        }

    }
}
