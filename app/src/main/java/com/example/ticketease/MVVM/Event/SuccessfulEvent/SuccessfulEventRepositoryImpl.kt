package com.example.ticketease.MVVM.Event.SuccessfulEvent

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Ticket.TicketCountWithPrice
import com.example.ticketease.MVVM.RetrofitAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SuccessfulEventRepositoryImpl(private val api : RetrofitAPI,
private val prefs : SharedPreferences) : SuccessfulEventRepository {
    override suspend fun createTicket(ticketWithPrice: TicketCountWithPrice)  = withContext(
        Dispatchers.IO){
        api.createTicket(ticketWithPrice)
    }
}