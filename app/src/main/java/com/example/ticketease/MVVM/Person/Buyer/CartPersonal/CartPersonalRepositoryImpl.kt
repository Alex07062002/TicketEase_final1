package com.example.ticketease.MVVM.Person.Buyer.CartPersonal

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Catalog
import com.example.ticketease.DataClasses.Event.EventId
import com.example.ticketease.DataClasses.Person.BuyerId
import com.example.ticketease.DataClasses.Person.BuyerUpdateCity
import com.example.ticketease.DataClasses.Person.BuyerWithoutPswd
import com.example.ticketease.DataClasses.Person.City
import com.example.ticketease.DataClasses.Ticket.TicketDTO
import com.example.ticketease.DataClasses.Ticket.TicketUpdate
import com.example.ticketease.MVVM.RetrofitAPI
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartPersonalRepositoryImpl(
    private val api : RetrofitAPI,
    private val prefs : SharedPreferences
) : CartPersonalRepository {
    override suspend fun countSoldTicket(eventId: EventId): Long  = withContext(Dispatchers.IO){
        api.countSoldTicket(eventId)
    }

    override suspend fun updateBuyerId(ticketUpdate: TicketUpdate): Unit = withContext(Dispatchers.IO){
        api.updateBuyerId(ticketUpdate)
    }
}