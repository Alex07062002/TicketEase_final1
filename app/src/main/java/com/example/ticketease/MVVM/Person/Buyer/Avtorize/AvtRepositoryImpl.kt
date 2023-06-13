package com.example.ticketease.MVVM.Person.Buyer.Avtorize

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Person.BuyerRequest
import com.example.ticketease.MVVM.RetrofitAPI
import com.google.gson.Gson

import retrofit2.HttpException

class AvtRepositoryImpl (private val api : RetrofitAPI,
                         private val prefs : SharedPreferences
): AvtRepository {
    override suspend fun avtorize(buyer: BuyerRequest): AvtResult<Unit> {
        return try {
            val response = api.avtorize(buyer)
            prefs.edit().putString("buyer", Gson().toJson(response)).apply()
            prefs.edit().putString("token", response.token).apply()


            AvtResult.Avtorized()
        } catch (e: HttpException) {
            if (e.code() == 401) AvtResult.IncorrectPassword() else
                AvtResult.UnknownLogin()
        }
    }
}