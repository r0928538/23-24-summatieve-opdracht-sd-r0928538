package be.svenlysiak.coolevents.data.userEvents

import kotlinx.coroutines.flow.Flow

class OfflineUserEventRepository (private val userEventDao: UserEventDao) : UserEventRepository {
    override fun getAllUserEventsStream(): Flow<List<UserEvent>> = userEventDao.getAllUserEvents()
    override fun getAllUserEventsForUserStream(username: String): Flow<List<UserEvent>> = userEventDao.getAllUserEventsForUser(username)

    override fun getAllUserEventsForCityStream(city: String): Flow<List<UserEvent>> = userEventDao.getAllUserEventsForCity(city)

    override suspend fun insertUserEvent(userEvent: UserEvent) = userEventDao.insertUserEvent(userEvent)

    override suspend fun deleteUserEvent(userEvent: UserEvent) = userEventDao.deleteUserEvent(userEvent)

    override suspend fun deleteAllForUser(username: String) = userEventDao.deleteAllForUser(username)
}