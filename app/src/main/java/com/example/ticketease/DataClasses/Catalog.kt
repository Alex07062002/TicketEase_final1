package com.example.ticketease.DataClasses


import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable


@Serializable
data class Catalog(
    @JsonProperty("eventId")
    val eventId : Long,
    @JsonProperty("name")
    val name : String,
    @JsonProperty("price")
    val price : Double,
    @JsonProperty("location")
    val location : String,
    @JsonProperty("date")
    val date : Long
    )
