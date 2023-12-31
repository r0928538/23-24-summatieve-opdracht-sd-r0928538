package be.svenlysiak.coolevents.data.users

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import be.svenlysiak.coolevents.data.users.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    //alle users tonen
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun findUser(username: String, password: String): Flow<List<User>>

    //user toevoegen
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    //user verwijderen
    @Delete
    suspend fun delete(user: User)

    //wachtwoord veranderen
    @Update
    suspend fun updateUser(user: User)

}