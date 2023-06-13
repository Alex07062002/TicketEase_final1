package com.example.ticketease.MVVM.Event.Catalog

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Catalog
import com.example.ticketease.DataClasses.Person.City
import com.example.ticketease.MVVM.RetrofitAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatalogRepositoryImpl(private val api : RetrofitAPI,
                            private val prefs :SharedPreferences
):CatalogRepository {
    override suspend fun getAllEvents(): List<Catalog> = withContext(Dispatchers.IO){
        val listCatalog = api.getAllEvents(City(prefs.getString("city", "")!!))
        listCatalog
    }
}