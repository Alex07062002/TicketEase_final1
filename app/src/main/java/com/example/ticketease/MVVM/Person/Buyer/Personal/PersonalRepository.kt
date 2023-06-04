package com.example.ticketease.MVVM.Person.Buyer.Personal

import com.example.ticketease.DataClasses.Catalog
import com.example.ticketease.DataClasses.Person.BuyerResponse
import com.example.ticketease.DataClasses.Person.BuyerWithoutPswd

interface PersonalRepository {
    suspend fun getByToken(buyer: BuyerResponse) : BuyerWithoutPswd

    suspend fun getAllEvents(): List<Catalog>

    suspend fun preferencesRoom() : List<Catalog>

    suspend fun selectEventByBuyer() : List<Long>

    // TODO Add getSoldTicket
}