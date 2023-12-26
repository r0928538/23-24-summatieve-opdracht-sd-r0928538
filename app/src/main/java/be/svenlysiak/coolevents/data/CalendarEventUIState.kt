package be.svenlysiak.coolevents.data

import be.svenlysiak.coolevents.models.Event

sealed interface EventApiState {
        data class Success(val eventsSuccess: List<Event>) : EventApiState
        object Error : EventApiState
        object Loading : EventApiState
}

data class CalendarEventUIState(
        val events : List<Event> = listOf<Event>(),
        val apiState : EventApiState = EventApiState.Loading
)