package be.svenlysiak.coolevents.models

import java.util.Date
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

/**
 * This data class defines a Mars photo which includes an ID, and the image URL.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class EventModel(
    val initiator: String?,
    val eventType: String,
    val recurrencePattern: String?,
    val status: String,
    val gipodId: Int,
    val owner: String,
    val description: String,
    val startDateTime: String,
    val endDateTime: String,
    val importantHindrance: Boolean,
    val coordinate: Coordinate,
    val detail: String,
    val cities: List<String>,
    val latestUpdate: String
)

data class Coordinate(
    val coordinates: List<Double>,
    val type: String,
    val crs: Crs
)

data class Crs(
    val type: String,
    val properties: Properties
)

data class Properties(
    val name: String
)