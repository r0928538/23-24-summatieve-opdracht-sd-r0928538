package be.svenlysiak.coolevents.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import be.svenlysiak.coolevents.data.CalendarEventUIState
import be.svenlysiak.coolevents.data.EventApiState
import be.svenlysiak.coolevents.models.Event
import be.svenlysiak.coolevents.network.EventApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class EventListViewModel : ViewModel() {
    //persistent (-> val) uistate with initial values
    private val _uistate : MutableStateFlow<CalendarEventUIState> =  MutableStateFlow(CalendarEventUIState((listOf())))
    //backing property
    val uistate : StateFlow<CalendarEventUIState> = _uistate

    init {
        viewModelScope.launch {

            try {
                val response = EventApi.eventService.getEventList()
                val eventList = ArrayList<Event>()
                val readingFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                for (event in response) {
                    val eventStartDateToDate =
                        LocalDate.parse(event.startDateTime.substring(0, 10), readingFormatter)
                    if (eventStartDateToDate >= LocalDate.now()) {
                        eventList.add(
                            Event(
                                event.recurrencePattern.toString().split("duur").first(),
                                event.eventType, event.status, event.owner,
                                event.description.trim(),
                                eventStartDateToDate,
                                LocalDate.parse(
                                    event.endDateTime.substring(0, 10),
                                    readingFormatter
                                ), event.importantHindrance,
                                event.cities.toString().replace("[", "").replace("]", "")
                            )
                        )
                    }
                }
                _uistate.update {
                    it.copy(apiState = EventApiState.Success(eventsSuccess = eventList))
                }
            } catch (e: Exception) {
                // Handle error
                _uistate.update {
                    it.copy(apiState = EventApiState.Error)
                }
            }
        }
    }
}
