package com.example.ticketease.MVVM.Person.Organizer.UpdateOrganizer

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Person.OrganizerWithoutPswd
import com.example.ticketease.MVVM.RetrofitAPI
import com.google.gson.Gson
import retrofit2.HttpException

class UpdateOrgRepositoryImpl (private val api : RetrofitAPI,
                               private val prefs : SharedPreferences
): UpdateOrgRepository {
    override suspend fun UpdateOrg(org: OrganizerWithoutPswd): UpdateOrgResult<Unit> {
        try {
            val response = api.orgUpdate(org)
            prefs.edit().putString("organizer", Gson().toJson(response)).apply()
            return  UpdateOrgResult.Correct()
        } catch (e: HttpException) {
            return UpdateOrgResult.Uncorrect()
        }

    }
}