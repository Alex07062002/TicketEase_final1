package com.example.ticketease.MVVM.Event.getEvents

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.PlaceTime.PlaceDTO
import com.example.ticketease.DataClasses.PlaceTime.PlaceType
import com.example.ticketease.MVVM.RetrofitAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class getEventsRepositoryImpl(private val api : RetrofitAPI,
                              private val prefs : SharedPreferences
):getEventsRepository {
    override suspend fun get(place: PlaceType?): List<PlaceDTO> = withContext(Dispatchers.IO) {
        api.getPlace(place)
    }
}