package be.svenlysiak.coolevents.data.userEvents

import be.svenlysiak.coolevents.data.users.User
import kotlinx.coroutines.flow.Flow

interface UserEventRepository {
    fun getAllUserEventsStream(): Flow<List<UserEvent>>
    fun getAllUserEventsForUserStream(username: String): Flow<List<UserEvent>>

    fun getAllUserEventsForCityStream(city: String): Flow<List<UserEvent?>>


    suspend fun insertUserEvent(userEvent: UserEvent)

    suspend fun deleteUserEvent(userEvent: UserEvent)

    suspend fun deleteAllForUser(username: String)
}