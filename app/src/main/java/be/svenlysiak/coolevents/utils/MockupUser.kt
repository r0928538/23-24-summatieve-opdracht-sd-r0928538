package be.svenlysiak.coolevents.utils

import be.svenlysiak.coolevents.data.User

class MockupUser {
    companion object {
        private val user1 = User( "sven", "password")
        private val users = arrayOf(user1)

        fun getUser(userName: String, password: String?) : User? {
            var user : User? = null
            var filterUsers = users.filter {
                it.username.lowercase().equals(userName?.lowercase()) &&  it.password.equals(password)
            }
            if (!filterUsers.isEmpty()) {
                user = filterUsers[0]
            }
            return user
        }

        fun getUsers() : List<User> {
            return users.toList()
        }
    }


}