package com.example.ticketease.MVVM.Event.Catalog

import com.example.ticketease.DataClasses.Catalog
import kotlinx.coroutines.flow.Flow

interface CatalogRepository {
     suspend fun getAllEvents(): List<Catalog>


}