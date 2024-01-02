package be.svenlysiak.coolevents.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.svenlysiak.coolevents.data.MyConfiguration
import be.svenlysiak.coolevents.data.users.User
import be.svenlysiak.coolevents.data.users.UserRepository
import kotlinx.coroutines.launch

class SettingsViewModel(private val repository: UserRepository) : ViewModel() {
    var city by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateCity(city: String) {
        this.city = city
    }

    fun updatePassword(password: String) {
        this.password = password
    }

    fun updateUserCity(){
        viewModelScope.launch() {
            if(MyConfiguration.loggedInUser != null)
            {
                val changedUser = User(
                    MyConfiguration.loggedInUser!!.username,
                    MyConfiguration.loggedInUser!!.password,
                    city)
                repository.updateUser(changedUser)
                MyConfiguration.loggedInUser = changedUser
            }
        }
    }

    fun updateUserPassword(){
        viewModelScope.launch() {
            if(MyConfiguration.loggedInUser != null)
            {
                val changedUser = User(
                    MyConfiguration.loggedInUser!!.username,
                    password,
                    MyConfiguration.loggedInUser!!.city)
                repository.updateUser(changedUser)
                MyConfiguration.loggedInUser = changedUser
            }
        }
    }

    fun deleteUser(){
        viewModelScope.launch {
            if(MyConfiguration.loggedInUser != null){
                repository.deleteUser(MyConfiguration.loggedInUser!!)
                MyConfiguration.loggedInUser = null
            }
        }
    }
}