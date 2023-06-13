package com.example.ticketease.MVVM.Person.Organizer.CitySelectorOrg

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Person.OrganizerUpdateCity
import com.example.ticketease.MVVM.RetrofitAPI

class CitySelectorOrgRepositoryImpl (
    private val api : RetrofitAPI,
    private val prefs : SharedPreferences
) : CitySelectorOrgRepository {
    override suspend fun update(organizer: OrganizerUpdateCity?): Boolean {

        return api.organizersUpdateCity(organizer)
    }
}