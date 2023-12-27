package be.svenlysiak.coolevents.data

import be.svenlysiak.coolevents.models.Event

class MyConfiguration {
    companion object {
        //hier ingelogde users, settings etc
        var loggedInUser : User? = null
        var selectedEvent : Event? = null
    }
}