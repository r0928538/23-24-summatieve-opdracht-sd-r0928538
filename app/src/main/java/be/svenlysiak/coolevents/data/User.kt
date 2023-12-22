package be.svenlysiak.coolevents.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/*data class User(var id: Int, var userName: String, var password: String, var isActive: Boolean)  {

    constructor(): this(0, "", "",false)
}*/

@Entity(tableName = "users")
data class User(
    @PrimaryKey val username: String,
    @ColumnInfo(name = "password") val password: String,
)
