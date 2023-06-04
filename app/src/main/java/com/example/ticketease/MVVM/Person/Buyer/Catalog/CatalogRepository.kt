package com.example.ticketease.MVVM.Event.Catalog

import com.example.ticketease.DataClasses.Catalog

interface CatalogRepository {
     suspend fun getAllEvents(): List<Catalog>

     suspend fun preferencesRoom() : List<Catalog>

     suspend fun selectEventByBuyer() : List<Long>

     //TODO add fun getSoldTicket
}