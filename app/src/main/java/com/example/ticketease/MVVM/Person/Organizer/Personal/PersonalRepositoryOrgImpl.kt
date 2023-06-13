package com.example.ticketease.MVVM.Person.Organizer.Personal

import android.content.SharedPreferences
import androidx.compose.ui.res.painterResource
import com.example.ticketease.DataClasses.Person.OrganizerResponse
import com.example.ticketease.DataClasses.Person.OrganizerWithoutPswd
import com.example.ticketease.MVVM.RetrofitAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonalRepositoryOrgImpl (

    private val api : RetrofitAPI,
    private val prefs : SharedPreferences
    ) : PersonalRepositoryOrg {
        override suspend fun getByToken(): OrganizerWithoutPswd =
             api.orgByToken(OrganizerResponse(prefs.getString("token", null)!!))

}