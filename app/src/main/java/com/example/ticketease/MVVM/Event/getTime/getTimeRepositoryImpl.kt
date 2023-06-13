package com.example.ticketease.MVVM.Event.getTime

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.PlaceTime.PlaceId
import com.example.ticketease.DataClasses.PlaceTime.PlaceTimeDTO
import com.example.ticketease.MVVM.RetrofitAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class getTimeRepositoryImpl (private val api : RetrofitAPI,
                             private val prefs : SharedPreferences
): getTimeRepository {
    override suspend fun getTime(placeId : PlaceId): List<PlaceTimeDTO>  = withContext(Dispatchers.IO){
         api.getTime(placeId)
    }
}