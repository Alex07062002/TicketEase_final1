package com.example.ticketease.MVVM.Event.SelectTime

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Event.EventDTO
import com.example.ticketease.DataClasses.PlaceTime.PlaceTimeDTO
import com.example.ticketease.DataClasses.Ticket.TicketCountWithPrice
import com.example.ticketease.MVVM.RetrofitAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateEventImpl(
    private val api : RetrofitAPI,
    private val prefs : SharedPreferences
) : CreateEvent {
    override suspend fun placeTimeUpdate(placeTimeDTO: PlaceTimeDTO)  = withContext(Dispatchers.IO){
        api.placeTimeUpdate(placeTimeDTO)
    }

    override suspend fun eventCreate(eventDTO: EventDTO) : EventDTO = withContext(Dispatchers.IO){
       api.eventCreate(eventDTO)
    }

}