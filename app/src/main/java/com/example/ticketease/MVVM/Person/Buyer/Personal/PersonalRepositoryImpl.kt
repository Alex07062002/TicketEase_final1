package com.example.ticketease.MVVM.Person.Buyer.Personal

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Person.BuyerResponse
import com.example.ticketease.DataClasses.Person.BuyerWithoutPswd
import com.example.ticketease.MVVM.RetrofitAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonalRepositoryImpl (
    private val api : RetrofitAPI,
    private val prefs : SharedPreferences
        ) : PersonalRepository {
    override suspend fun getByToken(): BuyerWithoutPswd  = withContext(Dispatchers.IO) {
        api.buyerByToken(BuyerResponse(prefs.getString("token", null)!!))
    }
}
