package be.svenlysiak.coolevents.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.svenlysiak.coolevents.data.MyConfiguration
import be.svenlysiak.coolevents.data.users.User
import be.svenlysiak.coolevents.data.users.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun updateUserName(username: String) {
        this.username = username
    }

    fun updatePassword(password: String) {
        this.password = password
    }

    fun login(loginSuccess: () -> Unit){
        viewModelScope.launch() {
            if (!repository.getUserStream(username, password).first().isEmpty()) {
                val user1 = repository.getUserStream(username, password).first().first()
                MyConfiguration.loggedInUser = user1
                if (MyConfiguration.loggedInUser != null) {
                    loginSuccess()
                }
            }
        }
    }

    fun register(){
        viewModelScope.launch() {
            if (validateInput()) {
                val user = User(username, password, "")
                repository.insertUser(user)
            }
        }
    }

    fun validateInput(): Boolean{
        return username!=""
    }
}