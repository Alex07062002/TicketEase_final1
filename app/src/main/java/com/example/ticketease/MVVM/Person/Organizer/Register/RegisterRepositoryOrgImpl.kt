package com.example.ticketease.MVVM.Person.Organizer.Register

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Person.Organizer
import com.example.ticketease.MVVM.RetrofitAPI
import com.google.gson.Gson
import retrofit2.HttpException

class RegisterRepositoryOrgImpl (
    private val api : RetrofitAPI,
    private val prefs : SharedPreferences
) : RegisterRepositoryOrg {
    override suspend fun singInOrg(org: Organizer): RegistResultOrg<Unit> {
        return try{
            val response = api.singInOrg(org)
            prefs.edit().putString("organizer", Gson().toJson(response)).apply()
            prefs.edit().putString("token", response.token).apply()
            RegistResultOrg.Registered()
        }catch (e: HttpException){
            if (e.code() == 401) RegistResultOrg.Unregistered() else
                RegistResultOrg.UnknownError()
        }
    }
}