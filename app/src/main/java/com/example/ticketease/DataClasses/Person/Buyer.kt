package com.example.ticketease.DataClasses.Person

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class BuyerRequest(
    @JsonProperty("login")
    val login: String="",
    @JsonProperty("password" )
    val password: String=""
    )
@Serializable
data class BuyerResponse(
    @JsonProperty("token")
    var token : String=""
    )

@Serializable
data class Buyer(
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
    var city: Cities? = null
)

@Serializable
data class BuyerWithoutPswd(
    @JsonProperty("id")
    var id: Long? = null,
    @JsonProperty("name")
    var name : String="",
    @JsonProperty("surname")
    var surname: String="",
    @JsonProperty("email")
    var email: String="",
    @JsonProperty("mobile")
    var mobile: String? = null,
    @JsonProperty("token")
    var token: String=""
    )

@Serializable
data class BuyerUpdateCity(
    @JsonProperty ("token")
    var token: String="",
    @JsonProperty("city")
    var city: String =""
    )

@Serializable
data class BuyerId(
    @JsonProperty("id")
    val id : Long
)
