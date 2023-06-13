package com.example.ticketease.MVVM.Person.Buyer.Register

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Person.Buyer
import com.example.ticketease.MVVM.RetrofitAPI
import com.google.gson.Gson
import retrofit2.HttpException

class RegisterRepositoryImpl(
    private val api : RetrofitAPI,
    private val prefs : SharedPreferences
) : RegisterRepository {
    override suspend fun signIn(buyer: Buyer): RegistResult<Unit> {
        return try{
            val response = api.postBuyer(buyer)
            prefs.edit().putString("buyer",Gson().toJson(response)).apply()
            prefs.edit().putString("token", response.token).apply()
            RegistResult.Registered()
        }catch (e: HttpException){
            if (e.code() == 401) RegistResult.Unregistered() else
                RegistResult.UnknownError()
        }
    }
}