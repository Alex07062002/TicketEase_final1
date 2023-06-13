package com.example.ticketease.MVVM.Person.Organizer.Personal

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Person.OrganizerWithoutPswd
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelPersonalOrg @Inject constructor(
    private val prefs : SharedPreferences,
    private val repository: PersonalRepositoryOrg
) : ViewModel() {
    var city = prefs.getString("city", null)!!

    private val _personal = MutableLiveData<OrganizerWithoutPswd>()
    val personal: LiveData<OrganizerWithoutPswd>
        get() = _personal
    init {
        if (prefs.contains("organizer")) {
            _personal.value = Gson().fromJson(prefs.getString("organizer", null)!!, OrganizerWithoutPswd::class.java)
        } else {
            viewModelScope.launch {
                _personal.value = repository.getByToken()
                prefs.edit().putString("organizer",Gson().toJson(_personal.value, OrganizerWithoutPswd::class.java)).apply()
            }
        }
    }
}
