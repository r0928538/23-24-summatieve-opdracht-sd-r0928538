package be.svenlysiak.coolevents.data

import be.svenlysiak.coolevents.data.userEvents.UserEvent
import be.svenlysiak.coolevents.data.users.User

class MyConfiguration {
    companion object {
        //hier ingelogde users, settings etc
        var loggedInUser : User? = null
        var selectedEvent : Any? = null
        var debug : List<UserEvent> = listOf(UserEvent("sven",
            "test", "test", "2004-10-01",
            "2004-10-01", "test"))
    }
}