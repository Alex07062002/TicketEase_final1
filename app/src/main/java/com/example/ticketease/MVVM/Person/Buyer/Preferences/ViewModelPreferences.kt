package com.example.ticketease.MVVM.Event.Preferences

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticketease.DataClasses.Catalog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelPreferences @Inject constructor(
 private val repository: PreferencesRepository,
 private val prefs : SharedPreferences
) : ViewModel() {

 private val _preferences = MutableLiveData<List<Catalog>>()
 val preferences: LiveData<List<Catalog>>
  get() = _preferences
 init {
    viewModelScope.launch {
      val listTicket = repository.selectEventCountByBuyer()
      if (!prefs.contains("buyer") || listTicket<5){
          _preferences.value = repository.getAllEvents()
      }else {
          _preferences.value = repository.preferencesRoom()
      }
  }
 }
}