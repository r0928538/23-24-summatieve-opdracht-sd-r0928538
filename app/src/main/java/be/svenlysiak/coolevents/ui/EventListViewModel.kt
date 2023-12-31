package be.svenlysiak.coolevents.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import be.svenlysiak.coolevents.data.CalendarEventUIState
import be.svenlysiak.coolevents.data.EventApiState
import be.svenlysiak.coolevents.data.MyConfiguration
import be.svenlysiak.coolevents.data.userEvents.UserEventRepository
import be.svenlysiak.coolevents.models.Event
import be.svenlysiak.coolevents.network.EventApi
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.abs


class EventListViewModel(private val repository: UserEventRepository) : ViewModel() {
    //persistent (-> val) uistate with initial values
    private val _uistate : MutableStateFlow<CalendarEventUIState> =  MutableStateFlow(CalendarEventUIState((listOf()), listOf()))
    //backing property
    val uistate : StateFlow<CalendarEventUIState> = _uistate

    init {
        viewModelScope.launch {
            getUserEventsInUiState()
            try {
                val response = EventApi.eventService.getEventList()
                val eventList = ArrayList<Event>()


                var filterCity = false
                var latitude = 0.0
                var longtitude = 0.0

                if(MyConfiguration.loggedInUser != null){
                    if(MyConfiguration.loggedInUser!!.city != ""){
                        filterCity = true
                        val eventInCity = response.find { it.cities.contains(MyConfiguration.loggedInUser!!.city)}
                            if(eventInCity != null){
                            latitude = eventInCity.coordinate.coordinates[0]
                            longtitude = eventInCity.coordinate.coordinates[1]
                        }
                    }
                }
                val readingFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                for (event in response) {
                    val eventStartDateToDate =
                        LocalDate.parse(event.startDateTime.substring(0, 10), readingFormatter)
                    val eventEndDateToDate =
                        LocalDate.parse(event.startDateTime.substring(0, 10), readingFormatter)
                    if (eventEndDateToDate >= LocalDate.now()) {
                        if(!filterCity || (abs(latitude - event.coordinate.coordinates[0]) < 0.15 && abs(longtitude - event.coordinate.coordinates[1]) < 0.15)) {
                            eventList.add(
                                Event(
                                    event.recurrencePattern.toString().split("duur").first(),
                                    event.eventType, event.status, event.owner,
                                    event.description.trim(),
                                    eventStartDateToDate,
                                    eventEndDateToDate,
                                    event.importantHindrance,
                                    event.cities.toString().replace("[", "").replace("]", ""),
                                    event.coordinate.coordinates[0],
                                    event.coordinate.coordinates[1]
                                )
                            )
                        }
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

    private fun getUserEventsInUiState(){
        viewModelScope.launch {
            if (MyConfiguration.loggedInUser != null) {
                val eventList =
                    repository.getAllUserEventsStream().first().toList()
                MyConfiguration.debug = eventList
                _uistate.update {
                    it.copy(userEvents = eventList)
                }
            }
        }
    }
}
