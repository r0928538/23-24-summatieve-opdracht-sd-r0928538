package be.svenlysiak.coolevents.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.svenlysiak.coolevents.data.MyConfiguration
import be.svenlysiak.coolevents.data.MyConfiguration.Companion.loggedInUser
import be.svenlysiak.coolevents.data.User
import be.svenlysiak.coolevents.data.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
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

    fun debug(loginSuccess: () -> Unit){
        viewModelScope.launch() {
            if (!repository.getUserStream(username, password).first().isEmpty()) {
                val user1 = User(username, password)
                MyConfiguration.loggedInUser = user1
                if (MyConfiguration.loggedInUser != null) {
                    loginSuccess()
                }
            }
        }
    }

    fun login() {
        viewModelScope.launch(){
            if(repository.getUserStream(username, password).toList().isNotEmpty()){
                val user1 = User(username, password)
                MyConfiguration.loggedInUser = user1
                password = ""
                username = ""
            }
            else{
                MyConfiguration.loggedInUser = repository.getAllUsersStream().first().first()
                    password = ""
                    username = ""
            }
        }
    }

    fun register(){
        viewModelScope.launch() {
            if (validateInput()) {
                val user = User(username, password)
                repository.insertUser(user)
            }
        }
    }

    private fun validateInput(): Boolean{
        return username!=""
    }

    /*
    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
     */
}