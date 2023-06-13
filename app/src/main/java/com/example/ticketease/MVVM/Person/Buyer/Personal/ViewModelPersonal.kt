package com.example.ticketease.MVVM.Person.Buyer.Personal

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Person.BuyerWithoutPswd
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelPersonal @Inject constructor(
    private val repository: PersonalRepository,
    private val prefs : SharedPreferences
) : ViewModel() {
    var city  = prefs.getString("city",null)!!

    private val _personal = MutableLiveData<BuyerWithoutPswd>()
    val personal: LiveData<BuyerWithoutPswd>
        get() = _personal
    init {
        if (prefs.contains("buyer")) {
            _personal.value = Gson().fromJson(prefs.getString("buyer", null)!!, BuyerWithoutPswd::class.java)
        } else {
            viewModelScope.launch {
                _personal.value = repository.getByToken()
                prefs.edit().putString("buyer",Gson().toJson(_personal.value,BuyerWithoutPswd::class.java)).apply()
            }
        }
    }
}


