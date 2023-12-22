package be.svenlysiak.coolevents.data

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsersStream(): Flow<List<User>>

    fun getUserStream(username: String, password: String): Flow<List<User?>>

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun updateItem(user: User)
}