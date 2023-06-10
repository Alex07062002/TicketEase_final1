package com.example.ticketease.MVVM.Event.Catalog

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Catalog
import com.example.ticketease.DataClasses.Person.BuyerId
import com.example.ticketease.DataClasses.Person.BuyerUpdateCity
import com.example.ticketease.DataClasses.Person.BuyerWithoutPswd
import com.example.ticketease.DataClasses.Person.City
import com.example.ticketease.MVVM.Person.Buyer.BuyerRetrofitAPI
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class CatalogRepositoryImpl(private val api : BuyerRetrofitAPI,
                            private val prefs :SharedPreferences
):CatalogRepository {
    override suspend fun getAllEvents(): List<Catalog> = withContext(Dispatchers.IO){
        val listCatalog = api.getAllEvents(City(prefs.getString("city", "")!!))
        listCatalog
    }
}