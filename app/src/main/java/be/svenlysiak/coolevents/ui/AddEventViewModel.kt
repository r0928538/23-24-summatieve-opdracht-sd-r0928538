package be.svenlysiak.coolevents.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.svenlysiak.coolevents.data.MyConfiguration
import be.svenlysiak.coolevents.data.userEvents.UserEvent
import be.svenlysiak.coolevents.data.userEvents.UserEventRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddEventViewModel(private val repository: UserEventRepository) : ViewModel() {
    fun saveEvent(eventType: String, description: String,
                 sday: String, smonth: String, syear: String,
                 eday: String, emonth: String, eyear: String,
                  cities: String){
        viewModelScope.launch() {
            if (validateInput(eventType, description, cities)) {
                try {
                    val readingFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    var sdate = "$syear-"
                    if (smonth.length == 1) {
                        sdate += "0"
                    }
                    sdate += "$smonth-"
                    if (sday.length == 1) {
                        sdate += "0"
                    }
                    sdate += sday

                    var edate = "$eyear-"
                    if (emonth.length == 1) {
                        edate += "0"
                    }
                    edate += "$emonth-"
                    if (eday.length == 1) {
                        edate += "0"
                    }
                    edate += eday
                    val startdate = LocalDate.parse(sdate, readingFormatter)
                    val enddate = LocalDate.parse(edate, readingFormatter)
                    if (enddate >= startdate) {
                        val event = UserEvent(
                            MyConfiguration.loggedInUser!!.username, eventType, description,
                            sdate, edate, cities
                        )
                        repository.insertUserEvent(event)
                    }
                }
                catch(ex: Exception){
                }
            }
        }
    }

    private fun validateInput(eventType: String, description: String, cities: String): Boolean{
        return validateLoggedIn() &&
                validateNotEmpty(eventType, description, cities)// &&
    }

    private fun validateLoggedIn() : Boolean{
        return MyConfiguration.loggedInUser != null
    }

    private fun validateNotEmpty(eventType: String, description: String, cities: String) : Boolean{
        return eventType != "" && description != "" && cities != ""
    }
}