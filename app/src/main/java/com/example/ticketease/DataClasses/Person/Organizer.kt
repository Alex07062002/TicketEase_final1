package com.example.ticketease.DataClasses.Person

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class OrganizerRequest(
    @JsonProperty("login")
    var login: String="",
    @JsonProperty("password")
    var password: String=""
    )
@Serializable
data class OrganizerResponse(
    @JsonProperty("token")
    var token: String )
@Serializable
data class OrganizerWithoutPswd(
    @JsonProperty("id")
    var id: Long,
    @JsonProperty("name")
    var name: String="",
    @JsonProperty("surname")
    var surname: String="",
    @JsonProperty("email")
    var email: String="",
    @JsonProperty("mobile")
    var mobile: String? = null,
    @JsonProperty("status")
    var status: StatusOrganizer = StatusOrganizer.CONSIDERATION,
    @JsonProperty("token")
    var token: String=""
    )
@Serializable
data class OrganizerUpdateCity(
    @JsonProperty("token")
    val token: String="",
    @JsonProperty("city")
    val city : Cities?= null
    )


@Serializable
data class Organizer(
    @JsonProperty("id")
    var id: Long? = null,
    @JsonProperty("name")
    var name: String = "",
    @JsonProperty("surname")
    var surname: String = "",
    @JsonProperty("login")
    var login: String = "",
    @JsonProperty("password")
    var password: String = "",
    @JsonProperty("email")
    var email: String = "",
    @JsonProperty("mobile")
    var mobile: String = "",
    @JsonProperty("city")
    var city: Cities? = null,
    @JsonProperty("status")
    var status: StatusOrganizer? = null,
    @JsonProperty("secret")
    var secret: String?  =""
)
