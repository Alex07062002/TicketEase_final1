package com.example.ticketease.MVVM.Person.Organizer.Avtorize

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Person.OrganizerRequest
import com.example.ticketease.MVVM.RetrofitAPI
import com.google.gson.Gson
import retrofit2.HttpException

class AvtRepositoryOrgImpl (private val api : RetrofitAPI,
                            private val prefs : SharedPreferences
): AvtRepositoryOrg {
    override suspend fun avtorizeOrg(organize: OrganizerRequest): AvtResultOrg<Unit> {
        return try {
            val response = api.avtorizeOrg(organize)
            prefs.edit().putString("organizer", Gson().toJson(response)).apply()
            prefs.edit().putString("token", response.token).apply()
            AvtResultOrg.Avtorized()
        } catch (e: HttpException) {
            if (e.code() == 401) AvtResultOrg.IncorrectPassword() else
                AvtResultOrg.UnknownLogin()
        }
    }
}