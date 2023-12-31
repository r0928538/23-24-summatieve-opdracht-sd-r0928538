package be.svenlysiak.coolevents.data.users

import kotlinx.coroutines.flow.Flow

class OfflineUserRepository (private val userDao : UserDao) : UserRepository {
    override fun getAllUsersStream(): Flow<List<User>> = userDao.getAllUsers()

    override fun getUserStream(username: String, password: String): Flow<List<User>> = userDao.findUser(username, password)

    override suspend fun insertUser(user: User) = userDao.insertUser(user)

    override suspend fun deleteUser(user: User) = userDao.delete(user)

    override suspend fun updateItem(user: User) = userDao.updateUser(user)
}