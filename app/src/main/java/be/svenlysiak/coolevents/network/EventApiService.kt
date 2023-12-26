package be.svenlysiak.coolevents.network

import be.svenlysiak.coolevents.models.EventModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://api.gipod.vlaanderen.be/"

val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

object EventApi {
    val eventService: EventApiService by lazy {
        retrofit.create(EventApiService::class.java)
    }
}

interface EventApiService {
    @GET("ws/v1/manifestation/")
    suspend fun getEventList(): List<EventModel>
}
