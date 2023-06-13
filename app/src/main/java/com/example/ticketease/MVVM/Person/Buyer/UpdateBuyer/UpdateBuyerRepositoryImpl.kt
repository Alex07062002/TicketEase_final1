package com.example.ticketease.MVVM.Person.Buyer.UpdateBuyer

import android.content.SharedPreferences
import com.example.ticketease.DataClasses.Person.BuyerWithoutPswd
import com.example.ticketease.MVVM.RetrofitAPI
import com.google.gson.Gson
import retrofit2.HttpException

class UpdateBuyerRepositoryImpl (private val api : RetrofitAPI,
                                 private val prefs : SharedPreferences
): UpdateBuyerRepository {
    override suspend fun UpdateBuyer(buyer: BuyerWithoutPswd): UpdateBuyerResult<Unit> {
         try {
            val response = api.buyerUpdate(buyer)
            prefs.edit().putString("buyer", Gson().toJson(response)).apply()
             return  UpdateBuyerResult.Correct()
        } catch (e: HttpException) {

             return UpdateBuyerResult.Uncorrect()
        }

    }
}