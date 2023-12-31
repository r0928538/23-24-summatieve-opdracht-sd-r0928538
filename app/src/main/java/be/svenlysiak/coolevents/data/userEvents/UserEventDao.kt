package be.svenlysiak.coolevents.data.userEvents

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserEventDao {
    //alle events tonen
    @Query("SELECT * FROM userEvents")
    fun getAllUserEvents(): Flow<List<UserEvent>>
    @Query("SELECT * FROM userEvents WHERE owner LIKE :username")
    fun getAllUserEventsForUser(username: String): Flow<List<UserEvent>>
    @Query("SELECT * FROM userEvents WHERE cities LIKE '%' || :city || '%'")
    fun getAllUserEventsForCity(city: String): Flow<List<UserEvent>>

    //event toevoegen
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserEvent(event: UserEvent)

    //event verwijderen
    @Delete
    suspend fun deleteUserEvent(event: UserEvent)

    @Query("DELETE FROM userEvents WHERE owner = :username")
    suspend fun deleteAllForUser(username: String)

}