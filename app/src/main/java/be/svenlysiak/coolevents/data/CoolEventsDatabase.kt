package be.svenlysiak.coolevents.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.svenlysiak.coolevents.data.userEvents.UserEvent
import be.svenlysiak.coolevents.data.userEvents.UserEventDao
import be.svenlysiak.coolevents.data.users.User
import be.svenlysiak.coolevents.data.users.UserDao

@Database(entities = [User::class, UserEvent::class], version = 1, exportSchema = false)
abstract class CoolEventsDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun userEventDao() : UserEventDao
    companion object {
        @Volatile
        private var Instance: CoolEventsDatabase? = null

        fun getDatabase(context: Context): CoolEventsDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CoolEventsDatabase::class.java, "cooleventsdb")
                    .build().also { Instance = it }
            }
        }
    }
}