package be.svenlysiak.coolevents.data.userEvents

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import be.svenlysiak.coolevents.data.users.User

@Entity(tableName = "userEvents",
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["username"],
            childColumns = ["owner"], onDelete = ForeignKey.CASCADE)])
data class UserEvent(
    @ColumnInfo(name = "owner") val owner: String,
    @ColumnInfo(name = "eventType") val eventType: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "startDateTime") val startDateTime: String,
    @ColumnInfo(name = "endDateTime") val endDateTime: String,
    @ColumnInfo(name = "cities") val cities: String,
    @PrimaryKey(autoGenerate = true)var eventId: Int = 0

)