package be.svenlysiak.coolevents.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.svenlysiak.coolevents.R
import be.svenlysiak.coolevents.data.MyConfiguration
import be.svenlysiak.coolevents.models.Event

@Composable
fun DetailScreen(modifier: Modifier = Modifier){
    SingleEventCard(event = MyConfiguration.selectedEvent!!)
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
        showdate += " " + androidx.compose.ui.res.stringResource(id = be.svenlysiak.coolevents.R.string.to) + " " + event.endDateTime.toString()
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
    Text(stringResource(id = R.string.organisator) + event.owner)
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