package com.example.ticketease.MVVM.Person.Organizer.Personal

import com.example.ticketease.DataClasses.Person.BuyerResponse
import com.example.ticketease.DataClasses.Person.BuyerWithoutPswd
import com.example.ticketease.DataClasses.Person.OrganizerResponse
import com.example.ticketease.DataClasses.Person.OrganizerWithoutPswd

interface PersonalRepositoryOrg {
        suspend fun getByToken() : OrganizerWithoutPswd



}