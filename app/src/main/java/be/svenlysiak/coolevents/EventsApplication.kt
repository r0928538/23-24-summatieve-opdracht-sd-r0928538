package be.svenlysiak.coolevents

import android.app.Application
import be.svenlysiak.coolevents.data.AppContainer
import be.svenlysiak.coolevents.data.AppDataContainer

class EventsApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}