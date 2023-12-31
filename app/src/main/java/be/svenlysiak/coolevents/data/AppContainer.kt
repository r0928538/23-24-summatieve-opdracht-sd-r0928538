package be.svenlysiak.coolevents.data

import android.content.Context
import be.svenlysiak.coolevents.data.userEvents.OfflineUserEventRepository
import be.svenlysiak.coolevents.data.userEvents.UserEventRepository
import be.svenlysiak.coolevents.data.users.OfflineUserRepository
import be.svenlysiak.coolevents.data.users.UserRepository

interface AppContainer {
    val userRepository: UserRepository
    val userEventRepository: UserEventRepository
}
class AppDataContainer(private val context: Context) : AppContainer {
    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(CoolEventsDatabase.getDatabase(context).userDao())
    }
    override val userEventRepository: UserEventRepository by lazy {
        OfflineUserEventRepository(CoolEventsDatabase.getDatabase(context).userEventDao())
    }
}