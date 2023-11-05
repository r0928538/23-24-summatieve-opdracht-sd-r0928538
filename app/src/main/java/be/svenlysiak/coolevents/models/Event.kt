package be.svenlysiak.coolevents.models

import java.util.Date

data class Event(
    var organisator: String, var titel: String, var datum: Date, var plaats: String,
    var eventType: String, var status: String, var wegmoelijkheden: Boolean)  {
    constructor(): this("", "", Date(),"","","",false)
}