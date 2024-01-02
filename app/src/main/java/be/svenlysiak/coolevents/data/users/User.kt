package be.svenlysiak.coolevents.data.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val username: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "city") val city: String
)
