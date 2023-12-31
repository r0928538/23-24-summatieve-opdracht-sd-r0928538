package be.svenlysiak.coolevents.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.svenlysiak.coolevents.R
import be.svenlysiak.coolevents.data.MyConfiguration
import be.svenlysiak.coolevents.data.userEvents.UserEvent
import be.svenlysiak.coolevents.models.Event

@Composable
fun DetailScreen(modifier: Modifier = Modifier,
                 detailViewModel: DetailViewModel = viewModel(factory = AppViewModelProvider.Factory),
                 onClick: () -> Unit
){
    if(MyConfiguration.selectedEvent is Event){
        SingleEventCard(event = MyConfiguration.selectedEvent!! as Event)
    }
    else{
        SingleUserEventCard(userEvent = MyConfiguration.selectedEvent!! as UserEvent, detailViewModel, onClick)
    }
}

@Composable
fun SingleUserEventCard(userEvent: UserEvent, detailViewModel: DetailViewModel,
                        onClick: () -> Unit,
                        modifier: Modifier = Modifier){
    Card(modifier = modifier
        .padding(20.dp)) {
        Column(modifier = modifier
            .padding(5.dp)) {
            Text(userEvent.cities, fontSize = 20.sp)
            Spacer(Modifier.height(5.dp))
            Divider()
            Spacer(Modifier.height(5.dp))
            var showdate = userEvent.startDateTime
            if(userEvent.startDateTime != userEvent.endDateTime){
                showdate += " " + stringResource(id = R.string.to) + " " + userEvent.endDateTime
            }
            Text(showdate)
            Spacer(Modifier.height(5.dp))
            Divider()
            Spacer(Modifier.height(15.dp))
            Text(stringResource(id = R.string.owner) + userEvent.owner)
            Spacer(Modifier.height(10.dp))
            Text(stringResource(id = R.string.owner) + userEvent.eventType)
            Spacer(Modifier.height(10.dp))
            Text(userEvent.description)
            Spacer(Modifier.height(15.dp))
            Divider()
            Spacer(Modifier.height(5.dp))
            DeleteButton(onClick = onClick.also { detailViewModel.deleteEvent(userEvent)})
        }

    }
}

@Composable
fun DeleteButton(onClick : () -> Unit) {
    Button(onClick = onClick) {
        Text(stringResource(R.string.delete))
    }
}

@Composable
fun SingleEventCard(event: Event, modifier: Modifier = Modifier){
    Card(modifier = modifier
        .padding(20.dp)) {
        Column(modifier = modifier
            .padding(5.dp)) {
            Text(event.cities, fontSize = 20.sp)
            TimePart(event = event)
            DetailsPart(event = event)
        }

    }
}
@Composable
fun TimePart(event: Event, modifier: Modifier = Modifier){
    Spacer(Modifier.height(5.dp))
    Divider()
    Spacer(Modifier.height(5.dp))
    Text(stringResource(id = R.string.eventStatus) + event.status)
    var showdate = event.startDateTime.toString()
    if(event.startDateTime != event.endDateTime){
        showdate += " " + stringResource(id = R.string.to) + " " + event.endDateTime.toString()
    }
    Text(showdate)
    Spacer(Modifier.height(5.dp))
    Divider()
    Spacer(Modifier.height(5.dp))
    if(event.recurrencePattern.toString() != "null"){
        Text(event.recurrencePattern!!)
        Spacer(Modifier.height(5.dp))
        Divider()
        Spacer(Modifier.height(5.dp))
    }
}
@Composable
fun DetailsPart(event: Event, modifier: Modifier = Modifier){
    Spacer(Modifier.height(10.dp))
    Text(stringResource(id = R.string.owner) + event.owner)
    Spacer(Modifier.height(10.dp))
    Text(event.description)
    Spacer(Modifier.height(15.dp))
    Divider()
    Spacer(Modifier.height(5.dp))
    if(event.importantHindrance){
        Text(stringResource(id = R.string.hindrance) + stringResource(id = R.string.yes))
    }
    else{
        Text(stringResource(id = R.string.hindrance) + stringResource(id = R.string.no))
    }
}