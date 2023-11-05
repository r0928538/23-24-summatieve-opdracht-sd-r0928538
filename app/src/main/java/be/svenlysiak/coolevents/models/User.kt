package be.svenlysiak.coolevents.models

import androidx.compose.foundation.isSystemInDarkTheme

data class User(var id: Int, var userName: String, var password: String, var isActive: Boolean)  {

    constructor(): this(0, "", "",false)
}