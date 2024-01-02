package be.svenlysiak.coolevents.models

import java.time.LocalDate

data class Event(
    val recurrencePattern: String?,
    val eventType: String,
    val status: String,
    val owner: String,
    val description: String,
    val startDateTime: LocalDate,
    val endDateTime: LocalDate,
    val importantHindrance: Boolean,
    val cities: String,
    val coordinateLatitude: Double,
    val coordinateLongtitude: Double
)