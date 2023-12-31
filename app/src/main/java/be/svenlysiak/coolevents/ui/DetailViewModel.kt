package be.svenlysiak.coolevents.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.svenlysiak.coolevents.data.userEvents.UserEvent
import be.svenlysiak.coolevents.data.userEvents.UserEventRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: UserEventRepository) : ViewModel() {
    fun deleteEvent(userEvent: UserEvent){
        viewModelScope.launch() {
                repository.deleteUserEvent(userEvent)
            }
        }
    }